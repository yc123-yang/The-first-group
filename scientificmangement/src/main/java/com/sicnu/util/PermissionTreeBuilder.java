package com.sicnu.util;

import com.sicnu.pojo.PermissionTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限树构造类
 */
public class PermissionTreeBuilder {

    /**
     * 构造权限树
     * 将权限节点列表转化为权限树
     *
     * @return
     */
    public static List<PermissionTreeNode> build(List<PermissionTreeNode> pNodes, int rootId) {
        pNodes.sort((p1, p2) -> p1.getId() - p2.getId());
        for (PermissionTreeNode n1 : pNodes) {
            for (PermissionTreeNode n2 : pNodes) {
                if (n2.getPid() == n1.getId()) {
                    n1.getChildrenPermissions().add(n2);
                }
            }
        }
        List<PermissionTreeNode> permissionTreeNodes = new ArrayList<>();
        for (PermissionTreeNode n : pNodes) {
            if (n.getPid() == rootId) {
                permissionTreeNodes.add(n);
            }
        }
        return permissionTreeNodes; //根节点id不存在可以抛出异常
    }

}
