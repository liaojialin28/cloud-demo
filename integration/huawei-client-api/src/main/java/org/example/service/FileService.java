package org.example.service;

import com.obs.services.model.DeleteObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    String fileUpload(MultipartFile file);


    List<String> fileUploadUseThreadBatch(List<MultipartFile> files);


    List<String> fileUploadBatch(List<MultipartFile> files);

    DeleteObjectResult fileDelete(String fileName);
}
