package com.atguigu.guli.service.oss.service;

import java.io.InputStream;

/**
 * description:
 * Created by Kris on 2021/3/9
 */
public interface FileService {
    String upload(InputStream inputStream, String module, String oFilename);

    void delete(String path,String module);
}
