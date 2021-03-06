package com.livk.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * <p>
 * UserVO
 * </p>
 *
 * @author livk
 * @date 2022/5/12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {

    private String username;

    private String type;

    private String createTime;

}
