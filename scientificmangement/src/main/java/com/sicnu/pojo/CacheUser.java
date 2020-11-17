package com.sicnu.pojo;

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

    private Integer cid;

    private Integer user_id;

    private String user_act;

    private String user_name;

    private String user_email;

    private Integer user_state;

    private String token;
}
