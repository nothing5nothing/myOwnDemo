package com.jcx.military.project.S3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AWSClient {

    private final static int MB_of_2 = 2*1024*1024;

    static AmazonS3 s3;
    private static void init() throws Exception {
        AWSCredentials credentials = new BasicAWSCredentials("access_key",
                "secret_key");

        ClientConfiguration configuration = new ClientConfiguration();
        configuration.setUseExpectContinue(false);

        String endPoint = "127.0.0.1:8002";
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                endPoint, null);

        s3 = AmazonS3ClientBuilder.standard().withEndpointConfiguration(endpointConfiguration)
                .withClientConfiguration(configuration).withCredentials(new AWSStaticCredentialsProvider(credentials))
                //.withChunkedEncodingDisabled(true)
                .withPathStyleAccessEnabled(true).build();
    }

    public static void deleteObject(String bucket, String object) {
        try {
            s3.deleteObject(bucket, object);
        } catch (AmazonServiceException e) {
            System.out.println("status code:" + e.getStatusCode());
        } catch (AmazonClientException e2) {
            System.out.println("status code:" + e2.getMessage());
        }
    }

    public static void putObject(String bucket, String object) {
        try {
            PutObjectRequest request = new PutObjectRequest(bucket, object,
                    new File("C:\\Users\\C\\Desktop\\files\\testfile.png"));
            s3.putObject(request);
        } catch (AmazonServiceException e) {
            System.out.println("status code:" + e.getStatusCode());
        } catch (AmazonClientException e2) {
            System.out.println("status code:" + e2.getMessage());
        }
    }

    public static void getObject(String bucket, String object) {
        try {
            GetObjectRequest request = new GetObjectRequest(bucket, object, null);
            System.out.println(object.toString());
            S3Object result = s3.getObject(request);

            S3ObjectInputStream s3is = result.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\C\\Desktop\\files\\" + object));
            byte[] read_buf = new byte[1024 * 34];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void listObjects(String bucket) {
        try {
            ListObjectsV2Request request = new ListObjectsV2Request();
            request.setBucketName(bucket);
            ListObjectsV2Result result = s3.listObjectsV2(request);

            List<String> commonPrefix = result.getCommonPrefixes();
            for (int i = 0; i < commonPrefix.size(); i++) {
                System.out.println("commonPrefix:" + commonPrefix.get(i));
            }
            List<S3ObjectSummary> objectList = result.getObjectSummaries();
            for (int i = 0; i < objectList.size(); i++) {
                System.out.println("key:" + objectList.get(i).getKey());
            }
        } catch (AmazonServiceException e) {
            System.out.println("status code:" + e.getStatusCode());
        } catch (AmazonClientException e2) {
            System.out.println("status code:" + e2.getMessage());
        }
    }

    public static void partUpload(String bucket, String keyName) throws FileNotFoundException {
        //初始化一个分块上传，获取分块上传ID，桶名 + 对像名 + 分块上传ID 唯一确定一个分块上传
        InitiateMultipartUploadRequest  initRequest = new InitiateMultipartUploadRequest("your-bucketname", "your-objectname");
        //您还可以在初始化分块上传时，设置文件的Content-Type
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("application/xml");
        initRequest.setObjectMetadata(objectMetadata);
        InitiateMultipartUploadResult initResult = s3.initiateMultipartUpload(initRequest);
        String uploadId = initResult.getUploadId();

        long filePosition = 0;
        List<PartETag> partETags = new ArrayList<>();

        for (int i = 1; i<1; i++) {
            // Last part can be less than 5 MB. Adjust part size.
            // Create request to upload a part.
            UploadPartRequest uploadRequest = new UploadPartRequest()
                    .withBucketName(bucket).withKey(keyName)
                    .withUploadId(uploadId).withPartNumber(i)
                    .withFileOffset(filePosition)
                    .withFile(new File("")) //要上传的文件对象
                    .withPartSize(MB_of_2);
            //如果是加密的，需要加入这步
//            if (filePosition + partSize == contentLength){
//                uploadRequest.setLastPart(true);
//            }
            // Upload part and add response to our list.
            partETags.add(s3.uploadPart(uploadRequest).getPartETag());

            filePosition += MB_of_2;
        }

        //这里可以检查分块是否全部上传，分块MD5是否与本地计算相符，如果不符或者缺少可以重新上传
        int nextMarker = 0;
        // 检查分片上传
        while (true) {
            ListPartsRequest listPartsRequest = new ListPartsRequest(bucket, keyName, uploadId);
            listPartsRequest.setPartNumberMarker(nextMarker);
            PartListing partList = s3.listParts(listPartsRequest);
            for (PartSummary ps : partList.getParts()) {
                nextMarker++;
                partETags.add(new PartETag(ps.getPartNumber(), ps.getETag()));
            }
            if (!partList.isTruncated()) {
                break;
            }
        }

        // 完成分片上传
        CompleteMultipartUploadRequest completeRequest =  new CompleteMultipartUploadRequest(
                bucket, keyName, uploadId, partETags);
        CompleteMultipartUploadResult completeResult = s3.completeMultipartUpload(completeRequest);

    }

    public static List<PartETag> getUploadStatus(String bucket, String keyName, String uploadId){
        List<PartETag> partETags = new ArrayList<>();
        int nextMarker = 0;
        while (true) {
            ListPartsRequest listPartsRequest = new ListPartsRequest(bucket, keyName, uploadId);
            listPartsRequest.setPartNumberMarker(nextMarker);
            PartListing partList = s3.listParts(listPartsRequest);
            for (PartSummary ps : partList.getParts()) {
                nextMarker++;
                partETags.add(new PartETag(ps.getPartNumber(), ps.getETag()));
            }
            if (!partList.isTruncated()) {
                break;
            }
        }
        return partETags;
    }

    public static void putBucket(String bucket) {
        try {
            s3.createBucket(bucket);
        } catch (AmazonServiceException e) {
            System.err.println(e.getStatusCode());
            System.err.println(e.getErrorCode());
            System.err.println(e.getErrorMessage());
        }
    }
    //运行主函数
    public static void main(String[] args) throws Exception {
        String bucketName = "mybucket";
        String keyName = "example.png";
        //初始化连接
        init();
        //创建桶
        putBucket(bucketName);
        //添加对象
        putObject(bucketName, keyName);
        //获取对象
        getObject(bucketName, keyName);
        //删除对象
        deleteObject(bucketName, keyName);
        //枚举对象列表
        listObjects(bucketName);
    }
}
