package com.sicnu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限树节点类
 */
//@Data
@AllArgsConstructor
public class PermissionTreeNode {

    private int id; //当前权限id
    private int pid; //父权限id
    private String title; //当前权限名
    private String resourcePath; //权限资源路径
    private boolean isOpen; //是否开放
    //    private Permission permission; //当前权限
    private List<PermissionTreeNode> childrenPermissions; //子权限

    public <E> PermissionTreeNode(Permission permission, ArrayList<E> es) {
        this.id = permission.getId();
        this.pid = permission.getPid();
        this.title = permission.getTitle();
        this.resourcePath = permission.getResourcePath();
        this.isOpen = permission.isOpen();
//        this.permission = permission;
        this.childrenPermissions = (List<PermissionTreeNode>) es;
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


//    public Permission getPermission() {
//        return permission;
//    }

//    public void setPermission(Permission permission) {
//        this.permission = permission;
//    }

    public List<PermissionTreeNode> getChildrenPermissions() {
        return childrenPermissions;
    }

    public void setChildrenPermissions(List<PermissionTreeNode> childrenPermissions) {
        this.childrenPermissions = childrenPermissions;
    }

    @Override
    public String toString() {
        return "PermissionTreeNode{" +
//                "permission=" + permission +
                ", childrenPermissions=" + childrenPermissions +
                '}';
    }
}
