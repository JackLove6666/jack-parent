package com.cloud.jack.app.service;

import com.cloud.jack.app.common.R;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {

    R downLoad(HttpServletResponse response);

    R uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("uploadPath") String uploadPath);
}
