package com.sicnu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 缓存用户信息
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/7/3 14:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheUser implements Serializable {

    private static final long serialVersionUID = 10L;

    private Integer cid;

    private Integer user_id;

    private String user_act;

    private String user_name;

    private String user_email;

    private Integer user_state;

    private String user_token;


}
