package com.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author XJ
 */
@Data
public class Login implements Serializable {
    private String userName;
    private String passWord;
}
