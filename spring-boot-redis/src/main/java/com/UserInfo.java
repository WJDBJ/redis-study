package com;

import lombok.Data;

import java.io.Serializable;

/**
 * @author XJ
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;
    private String name;
}
