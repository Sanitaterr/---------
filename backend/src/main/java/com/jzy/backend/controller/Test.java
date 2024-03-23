package com.jzy.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/18 21:16
 * @Description: TODO
 */
@RestController
public class Test {

    @PostMapping("/test/")
    @PreAuthorize("hasAuthority('test')")
    public String test(){
        return "Hello World";
    }
}
