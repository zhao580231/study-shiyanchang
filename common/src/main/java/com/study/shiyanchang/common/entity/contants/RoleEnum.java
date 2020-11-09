package com.study.shiyanchang.common.entity.contants;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    ROOT(0, "运营商管理员"),
    COMPANY_ADMIN(1, "公司管理员"),
    WORKER(2, "工作人员");
    private Integer id;
    private String roleName;
    RoleEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    private static final Map<Integer, String> map = new HashMap<>();
    static {
        for (RoleEnum s : RoleEnum.values()) {
            map.put(s.getId(), s.getRoleName());
        }
    }
    public int getId() {
        return id;
    }
    public String getRoleName() {
        return roleName;
    }
    public static String getRoleNameById(Integer id) {
        return map.get(id);
    }
}
