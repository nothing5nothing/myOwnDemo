package com.jcx.military.project.upload;


import com.jcx.military.project.data.FileHash;
import com.jcx.military.project.nas.NasUtil;
import com.jcx.military.project.upload.cache.FileContextCache;
import com.jcx.military.project.util.Md5Helper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

@Service
public class LocalUpload extends AbsUpload {


    @Override
    // 从redis位图中获取chunk
    public String getFileChunk() {



        return null;
    }

    @Override
    public FileContextCache initFileStatus() {

        // 获取分布式锁  +  double check

        // 初始化零时文件
        RandomAccessFile r = null;
        try {
            r = new RandomAccessFile(new File(""), "rw");
            r.setLength(1024);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (r != null) {
                    r.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建etag 刷新缓存


        return new FileContextCache();
    }

    @Override
    public void uploadPart(MultipartFile file,
                           String md5,
                           String etag,
                           long size,
                           int chunkSize,
                           int chunk,
                           String cachePath) {

        String path = NasUtil.getValue() + cachePath;
        try(RandomAccessFile randomAccessFile =
                    new RandomAccessFile(path,"w")){
            byte[] bytes = new byte[1024];
            int len = 0 ;
            while((len = file.getInputStream().read(bytes)) < 0) {
                randomAccessFile.write(bytes,chunk*chunkSize , len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





    @Override
    public FileHash completeUpload(String md5,
                            String etag,
                            long size,
                            int chunkSize,
                            int chunk,
                            String cachePath){

        //local 是否需要解密  --> 解密后文件直传判断  --> 文件上传
        boolean decrypt = true;

        // 文件解密 主要耗时时间
        File decryptFile = new File("decrypt");
        // 获取加密信息
        // 根据工号校验文件权限

        // 解密后文件直传判断 根据解密后md5查找ActualHash数据库是否存在，存在公用缓存返回数据

        String hashMd5 = Md5Helper.getFileMD5String(decryptFile);
//        FileHash fileHash = FileHash.builder()
//                .fielHash(hashMd5)
//                .sourceFileSize(decryptFile.length())
//                .turelyfileSize(size)
//                .sourceMd5(md5).build();
        FileHash fileHash = new FileHash();

        if(fileHash.isExist()) {
            return  fileHash;
        }

        // 调用S3上传完整文件 S3内部分片上传

        // 在S3测进行 服务端与S3 的端到端校验

        return  fileHash;
    }



    @Override
    public boolean checkFilePart(String md5,
                                 long size,
                                 int chunkSize,
                                 String cachePath,
                                 String bitmap){
        // 判断客户端与服务端 md5 校验
        String fileMd5 = Md5Helper.getFileMD5String(new File(cachePath));
        if(fileMd5 != md5) {
            return false;
        }

        // 校验文件存在 大小
        File file = new File(cachePath);
        if(!file.exists() || file.length() != size){
            return false;
        }

        // 校验redis
        // 获取位图
        return super.checkBitMap(bitmap,size,chunkSize);
    }

}
