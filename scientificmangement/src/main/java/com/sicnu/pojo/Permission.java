package com.sicnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 改 ——
 * 权限实体
 */
//@Data
//@AllArgsConstructor
public class Permission {

    //根节点权限id
    public final static int ROOTID = 0;

    private int id; //当前权限id
    private int pid; //父权限id
    private String title; //当前权限名
    private String resourcePath; //权限资源路径
    private boolean isOpen; //是否开放

    public static int getROOTID() {
        return ROOTID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "id=" + id +
//                ", pid=" + pid +
//                ", title='" + title + '\'' +
//                ", resourcePath='" + resourcePath + '\'' +
//                ", isOpen=" + isOpen +
//                '}';
//    }
}
