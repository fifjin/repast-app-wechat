package com.aaa.gj.repast.controller;

import com.aaa.gj.repast.base.BaseController;
import com.aaa.gj.repast.base.ResultData;
import com.aaa.gj.repast.service.IRepastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-22 22:44
 **/
@RestController
@Api(value = "文件上传",tags = "文件上传接口")
public class UploadController extends BaseController {
    @Autowired
    private IRepastService repastService;
    @PostMapping("/upload")
    @ApiOperation(value = "实现文件上传" ,notes = "单文件上传接口")
    public ResultData uploadFile(MultipartFile file,String token){
        Boolean ifSuccess = repastService.uploadFile(file, token);
        if (ifSuccess){
            return super.success();
        }
        return super.failed();
    }
}
