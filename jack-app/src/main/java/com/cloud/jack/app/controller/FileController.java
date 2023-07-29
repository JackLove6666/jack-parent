package com.cloud.jack.app.controller;


import com.cloud.jack.app.common.R;
import com.cloud.jack.app.common.exception.file.InvalidExtensionException;
import com.cloud.jack.app.config.SystemConfig;
import com.cloud.jack.app.constant.CommonConstant;
import com.cloud.jack.app.utils.file.FileUploadUtils;
import com.cloud.jack.app.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    /**
     * 本地资源下载
     * @param path
     * @param response
     */
    @GetMapping("/downLoad")
    public void downLoad(@RequestParam String path,HttpServletResponse response){
        try
        {
            // 本地资源路径
            String localPath = SystemConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(path, CommonConstant.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }


    @PostMapping("/uploadFile")
    public R uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("path") String path) throws IOException, InvalidExtensionException {
        String baseDir = "/jack";
        String[] allowedExtension= {"gif","png","jpg"};
        String folderPath = SystemConfig.getProfile() + baseDir;
        String upload = FileUploadUtils.upload(folderPath, file, allowedExtension);
        return R.ok(upload);
    }

    /**
     * 通用上传请求（多个）
     * @return
     */
    @PostMapping("/")
    public R uploadFiles(@RequestParam("file") MultipartFile files){
        //todo
        return null;
    }

}
