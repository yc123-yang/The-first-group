package com.sicnu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


//项目监听器
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("高校科研管理项目...web应用启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("高校科研管理项目...当前web项目销毁");
    }
}