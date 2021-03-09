package com.atguigu.guli.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.guli.service.oss.service.FileService;
import com.atguigu.guli.service.oss.utils.OssProperties;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.UUID;

/**
 * description:
 * Created by Kris on 2021/3/9
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    OssProperties ossProperties;
    private String schema;
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;

//    在执行构造器方法之后就先执行这个方法
    @PostConstruct
    public void init() {
        this.schema = ossProperties.getSchema();
        this.endpoint = ossProperties.getEndpoint();
        this.keyId = ossProperties.getKeyId();
        this.keySecret = ossProperties.getKeySecret();
        this.bucketName = ossProperties.getBucketName();
    }

    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建bucket
            ossClient.createBucket(bucketName);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        //构建日期路径：avatar/2019/02/26/文件名
        String folder = new DateTime().toString("yyyy/MM/dd");

        //文件名：uuid.扩展名
//        生成随机的文件名
        String fileName = UUID.randomUUID().toString();
//        对于上传的文件的原始名做字符串截取：截取.后的字符
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        module/yyyy/MM/dd/fileName.filextension
        String key = module + "/" + folder + "/" + fileName + fileExtension;

        //文件上传至阿里云
        ossClient.putObject(ossProperties.getBucketName(), key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回url地址
        return "https://" + bucketName + "." + endpoint + "/" + key;
    }

    @Override
    public void delete(String path,String module) {
        System.out.println("aaa"+path);
        String object = path.substring(path.lastIndexOf(module));
        System.out.println("aaa"+object);
//        创建oss实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);

//        ossClient.deleteObject(bucketName, object);
        ossClient.deleteObject(bucketName, object);

        ossClient.shutdown();
    }
}
