package com.aaa.gj.repast.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: repast-app-parent
 * @author: gj
 * @create: 2020-03-21 17:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class TokenVo implements Serializable {
    private String token;
    private boolean ifsuccess;
}
