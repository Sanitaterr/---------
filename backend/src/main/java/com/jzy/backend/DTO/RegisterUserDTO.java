package com.jzy.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/25 16:15
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO implements Serializable {
    private static final long serialVersionUID = 6412020780759001490L;

    private String username;

    private String password;

    private String confirmedPassword;
}
