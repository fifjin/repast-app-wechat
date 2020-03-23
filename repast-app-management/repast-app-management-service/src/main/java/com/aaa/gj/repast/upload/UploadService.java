package com.aaa.gj.repast.upload;

import com.aaa.gj.repast.properties.FtpProperties;
import com.aaa.gj.repast.utils.DateUtil;
import com.aaa.gj.repast.utils.FileNameUtil;
import com.aaa.gj.repast.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.gj.repast.staticstatus.RequesProperties.*;
import static com.aaa.gj.repast.staticstatus.StaticCode.FORMAT_DATE;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-22 23:06
 **/
@Service
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;
    /**
     * @ClassName UploadService
     * @Description : ftp文件上传
     *
     * @param file
     * @param token
     * @Return : java.lang.Boolean
     * @Author : gj
     * @Date : 2020/3/22 23:12
    */
    public Boolean upload(MultipartFile file, String token){
        //要防止文件名重复导致文件覆盖，所以要文件名替换
        String oldFileName = file.getOriginalFilename();
        String newFileNmae = FileNameUtil.getFileName(token);
        newFileNmae = newFileNmae + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //filePath
        String filePath = DateUtil.formatDate(new Date(),FORMAT_DATE);
        try {
            return FtpUtil.uploadFile(ftpProperties.getHost(),ftpProperties.getPort(),ftpProperties.getUsername()
            ,ftpProperties.getPassword(),ftpProperties.getBasePath(),filePath,newFileNmae,file.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
