package com.aaa.gj.repast.controller;

import com.aaa.gj.repast.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.aaa.gj.repast.staticstatus.RequesProperties.TOKEN;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-22 23:05
 **/
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping(value = "upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Boolean uploadFile(@RequestBody MultipartFile file, @RequestParam(TOKEN)String token){
        return uploadService.upload(file,token);
    }
}
