package com.sicnu.component;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存用户信息
 * <br/>
 * @author     ：leigq
 * @date       ：2019/7/3 14:40
 */
@Data
@Builder
public class CacheUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user_email;

    private String user_name;

    private Integer state;

    private String user_act;

    private String token;
}
