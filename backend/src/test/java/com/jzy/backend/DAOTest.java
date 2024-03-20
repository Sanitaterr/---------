package com.jzy.backend;

import com.jzy.backend.DAO.UserDAO;
import com.jzy.backend.DO.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void testUserDAO() {
        List<User> users = userDAO.selectList(null);
        System.out.println(users);
    }
}
