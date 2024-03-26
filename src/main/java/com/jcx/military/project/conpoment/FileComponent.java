package com.jcx.military.project.conpoment;



import com.jcx.military.project.data.FileHash;
import com.jcx.military.project.data.QueryFileStatus;
import com.jcx.military.project.redis.RedisService;
import com.jcx.military.project.upload.UploadUtil;
import com.jcx.military.project.upload.cache.FileContextCache;
import com.jcx.military.project.upload.util.UploadFactory;
import com.jcx.military.project.upload.util.UploadType;
import com.jcx.military.project.util.StringUtil;
import com.jcx.military.project.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

@Component
public class FileComponent {


    @Autowired
    RedisService redisService;


    public QueryFileStatus queryFileStatus(String fileName,
                                    int fileSize,
                                    String md5,
                                    int chunkSize){
        QueryFileStatus queryFileStatus = new QueryFileStatus();
        // 判断直传
        String appCode = ThreadLocalUtil.getAppcode();
        String user = ThreadLocalUtil.getUser();
        // 根据md5和fileSize查找数据库，如果有对应存储则直传
        if(true) {
            queryFileStatus.setDirect(true);
            return queryFileStatus;
        }

        // 判断文件是否加密
        Boolean encrypt = ThreadLocalUtil.getEncrypt();
        UploadUtil uploadUtil = UploadFactory.getUploadUtil(encrypt);

        // 获取文件上下文缓存 缓存为空则重新上传 不为空则判断文件状态
        FileContextCache fileContextCache = new FileContextCache();
        if(StringUtil.isNull(fileContextCache)){
            // 初始化上传状态
            fileContextCache = uploadUtil.initFileStatus();
            // 计算分片数量,位图初始化
            int count = fileSize/chunkSize;
            String key = md5+fileSize;
            redisService.setBitMap(key,count,false);
            return queryFileStatus;
        }
        // 获取文件分片 返回用户 chunk --null 没有上传过 有数据则返回数据
        String chunk = uploadUtil.getFileChunk();
        queryFileStatus = new QueryFileStatus();
        if (chunk != null ){
            queryFileStatus.setExist(true);
        }
        queryFileStatus.setStatus(chunk);

        // 刷新缓存
        return queryFileStatus;
    }


    public boolean sendPartData(MultipartFile file,
                         long fileSize,
                         String md5,
                         int chunk,
                         int chunkSize){

        // 校验是否直传


        // 获取上下问缓存
        String key = md5+fileSize;
        FileContextCache fileContextCache = (FileContextCache)redisService.getValue(key);

        if(fileContextCache == null) {
            throw new RuntimeException();
        }

        // 获取上传方式
        UploadType uploadType = fileContextCache.getUploadType();
        UploadUtil uploadUtil = UploadFactory.getUploadUtil(uploadType);
        // 上传分片文件
        String cachePath = "";
        uploadUtil.uploadPart( file, md5,StringUtil.getUUid() ,fileSize, chunkSize,chunk,cachePath);



        return true;
    }



    public String finishPartObject(String md5,
                                   long fileSize,
                                   int chunk,
                                   int chunkSize) {

        // 判断直传如果成功则直接创建关联对象


        // 获取文件缓存， 如果缓存不存在则报错
        String key = "FileObject::"+md5+fileSize;
        FileContextCache fileContextCache = (FileContextCache)redisService.getValue(key);

        if(fileContextCache == null) {
            throw new RuntimeException();
        }

        // 获取上传方式
        UploadType uploadType = fileContextCache.getUploadType();
        UploadUtil uploadUtil = UploadFactory.getUploadUtil(uploadType);

        // 文件完整校验
        String bitMapkey = "FileBitMap::" + md5 + fileSize;
        String bitmap = (String)redisService.getValue(bitMapkey);
        String cachePath = (String)redisService.getValue("PATH::"+md5+fileSize);
        boolean complete = uploadUtil.checkFilePart(md5, fileSize,chunkSize,cachePath,bitmap);
        if(!complete) {
            throw new RuntimeException();
        }


        // 整合文件分片
        FileHash fileHash = uploadUtil.completeUpload(md5, fileContextCache.getEtag(),fileSize,chunkSize,chunk,cachePath);


        // 创建文件对象， 创建缓存对象

        // 塞入数据库 塞入缓存

        return "";
    }



}
