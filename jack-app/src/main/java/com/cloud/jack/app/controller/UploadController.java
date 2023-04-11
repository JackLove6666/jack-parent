package com.cloud.jack.app.controller;

import com.cloud.jack.app.core.R;
import com.cloud.jack.app.service.UploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class UploadController {


    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "批量上传")
    @PostMapping("/batchImport")
    public R batchImport(List<MultipartFile> files) {
            return uploadService.batchImport(files);
    }




}
