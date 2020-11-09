package com.study.shiyanchang.common.entity.contants;

import java.util.HashMap;
import java.util.Map;

public enum UserStateEnum {
    NORMAL(0, "正常"),
    LOCK(1, "锁定");
    private Integer id;
    private String text;
    UserStateEnum(int id, String text) {
        this.id = id;
        this.text = text;
    }
    private static final Map<Integer, String> map = new HashMap<>();
    static {
        for (UserStateEnum s : UserStateEnum.values()) {
            map.put(s.getId(), s.getText());
        }
    }
    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public static String getTextById(Integer id) {
        return map.get(id);
    }
}
