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
        static final public String SUCCESSFUL_LOGIN = "登录成功";
        static final public String SUCCESSFUL_CANCELLATION = "注销成功";
        static final public String USER_AUTHENTICATION_FAILED_PLEASE_LOG_IN_AGAIN = "用户认证失败，请重新登录";
        static final public String YOU_DO_NOT_HAVE_ENOUGH_PERMISSIONS = "您的权限不足";
    }
}
