package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author haocunli
 * @date created in 2018/10/20 14:17
 * @since 1.0.0
 */

@Slf4j
@Service
public class FileUploadService {

    public String photoUpload(MultipartFile file) throws IllegalStateException, IOException{
        String fileName=file.getOriginalFilename();
        if (file!=null) {
            String path;
            String type;
            log.info("uploaded file name:"+fileName);
            
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1):null;
            if (type!=null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {

                    String realPath="E:\\ELEC5619\\assignment-master\\src\\main\\resources\\tmp\\";

                    path=realPath+fileName;
                    log.info("Path:"+path);

                    file.transferTo(new File(path));
                    log.info("successful");
                }else {
                    log.info("type is incorrect");

                }
            }else {
                log.error("file is null");

            }
        }else {
            log.error("No such file");

        }
        return "http://localhost:8080/tmp/"+fileName;
    }

}
