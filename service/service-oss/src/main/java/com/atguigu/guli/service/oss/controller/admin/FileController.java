package com.atguigu.guli.service.oss.controller.admin;

import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * description:
 * Created by Kris on 2021/3/9
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {

    @Autowired
    FileService fileService;

    //  进行文件上传
    @PostMapping("/upload")//文件上传需要将文件的报文也就是目录地址放在请求体中，所以需要post方法
//    不能以url地址方式传输，因为会经过urlencode编码，会造成文件丢失
    @ApiOperation("文件上传")
//    spring中采用MultipartFile进行文件上传，key的值必须是file
    public R upload(@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file,
                    @ApiParam(value = "模块", required = true) @RequestParam("module") String module) throws IOException {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
//        System.out.println("aaa"+originalFilename);
        String upload = fileService.upload(inputStream, module, originalFilename);
        return R.ok().message("上传成功").data("url", upload);
    }

    //    进行文件删除
    @DeleteMapping("/delete")
    @ApiOperation("文件删除")
    public R delete(@ApiParam(value = "文件路径", required = true)@RequestParam String path
            ,@ApiParam(value = "模块",required = true)@RequestParam String module) {
        fileService.delete(path,module);
        return R.ok().message("删除成功");
    }
}
