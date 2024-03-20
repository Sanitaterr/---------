package com.jzy.backend.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 测试用的
 * @author JZY
 * @version 1.0
 * Create by 2024/3/20 23:16
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = -2345346245624356L;

    @TableId
    private Long id;

    private String username;

    private String password;
}
