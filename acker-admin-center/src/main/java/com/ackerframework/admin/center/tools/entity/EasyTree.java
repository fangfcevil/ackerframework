package com.ackerframework.admin.center.tools.entity;


import com.ackerframework.base.entity.BaseEntity;

public class EasyTree extends BaseEntity {
    private String treeName;
    private String treeNode;

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(String treeNode) {
        this.treeNode = treeNode;
    }
}
