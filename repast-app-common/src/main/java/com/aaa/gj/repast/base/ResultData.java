package com.aaa.gj.repast.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-15 18:26
 **/
@Data
public class ResultData<T> implements Serializable {
    private String code;
    private String msg;
    private String detail;
    private T data;
}
