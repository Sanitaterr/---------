package com.jzy.backend.constance;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/23 15:41
 * @Description: TODO
 */
public class ResponseResultConstance {

    static public class code {
    }

    static public class msg {
        public static final String SUCCESSFUL_LOGIN = "登录成功";
        public static final String SUCCESSFUL_CANCELLATION = "注销成功";
        public static final String USER_AUTHENTICATION_FAILED_PLEASE_LOG_IN_AGAIN = "用户认证失败，请重新登录";
        public static final String YOU_DO_NOT_HAVE_ENOUGH_PERMISSIONS = "您的权限不足";
        public static final String SUCCESSFUL_REGISTRATION = "注册成功";
        public static final String USERNAME_CANNOT_BE_EMPTY = "用户名不能为空";
        public static final String PASSWORD_CANNOT_BE_EMPTY = "密码不能为空";
        public static final String TWO_PASSWORDS_ARE_NOT_EQUAL = "两次输入密码不相等";
        public static final String USERNAME_LENGTH_CANNOT_BE_GREATER_THAN_150 = "用户名长度不能大于150";
        public static final String PASSWORD_LENGTH_CANNOT_BE_GREATER_THAN_150 = "密码长度不能大于150";
        public static final String USERNAME_ALREADY_EXISTS = "用户名已存在";
    }
}
