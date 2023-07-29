package com.cloud.jack.app.service.impl;

import com.cloud.jack.app.common.R;
import com.cloud.jack.app.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public R downLoad(HttpServletResponse response) {
        return null;
    }

    @Override
    public R uploadFile(MultipartFile file, String uploadPath) {
        if(file == null){
            return R.fail("请选择文件上传!");
        }
        String originalFilename = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
