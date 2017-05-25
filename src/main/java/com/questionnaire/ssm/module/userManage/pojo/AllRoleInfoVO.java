package com.questionnaire.ssm.module.userManage.pojo;

/**
 * Created by 郑晓辉 on 2017/5/25.
 * Description: 全部角色信息视图类
 */
public class AllRoleInfoVO {
    //角色编号
    private Long roleId;
    //角色
    private String role;

    @Override
    public String toString() {
        return "AllRoleInfoVO{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
