package com.cloud.jack.app.service;

import com.cloud.jack.app.core.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {


    R batchImport(List<MultipartFile> files);
}
