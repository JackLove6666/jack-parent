package com.cloud.jack.app.service;

import com.cloud.jack.app.common.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {


    R batchImport(List<MultipartFile> files);

    R importExcel(MultipartFile file);

    /**
     * 批量导入退货单
     * @param file
     * @return
     */
    R importExcel2(MultipartFile file);
}
