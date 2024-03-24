package com.jzy.backend;

import com.jzy.backend.DAO.MenuDAO;
import com.jzy.backend.DAO.UserDAO;
import com.jzy.backend.DO.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/20 23:39
 * @Description: TODO
 */
@SpringBootTest
public class DAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode1 = passwordEncoder.encode("123");
        String encode2 = passwordEncoder.encode("123");
        System.out.println(encode1);
        System.out.println(encode2);
    }

    @Autowired
    private MenuDAO menuDAO;

    @Test
    public void testSelectPermsByUserId() {
        List<String> list = menuDAO.selectPermsByUserId(2L);
        System.out.println(list);
    }

    @Test
    public void testUserDAO() {
        List<User> users = userDAO.selectList(null);
        System.out.println(users);
    }
}
