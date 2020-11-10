package com.study.shiyanchang.common.base;

import com.github.phantomthief.scope.ScopeKey;
import org.springframework.util.StringUtils;

public class CurrentScope {

    private static final ScopeKey<Long> LOGIN_USER_ID = ScopeKey.withDefaultValue(0L);

    private static final ScopeKey<Long> LOGIN_USER_COM_ID = ScopeKey.withDefaultValue(0L);

    private static final ScopeKey<String> LOGIN_USER_TOKEN = ScopeKey.withDefaultValue("");

    private static final ScopeKey<String> LOGIN_USER_ADDR = ScopeKey.withDefaultValue("");

    private CurrentScope() {
        throw new UnsupportedOperationException();
    }

    public static long getLoginUserId() {
        return LOGIN_USER_ID.get();
    }

    public static void setLoginUserId(long loginUserId) {
        LOGIN_USER_ID.set(loginUserId);
    }

    public static ScopeKey<String> getLoginUserToken() {
        return LOGIN_USER_TOKEN;
    }

    public static void setLoginUserToken(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new IllegalArgumentException();
        }
        LOGIN_USER_TOKEN.set(token);
    }

    public static long getLoginUserComId() {
        return LOGIN_USER_COM_ID.get();
    }

    public static void setLoginUserComId(long loginUserComId) {
        LOGIN_USER_COM_ID.set(loginUserComId);
    }

    public static ScopeKey<String> getLoginUserAddr() {
        return LOGIN_USER_ADDR;
    }

    public static void setLoginUserAddr(String addr) {
        if (StringUtils.isEmpty(addr)) {
            throw new IllegalArgumentException();
        }
        LOGIN_USER_ADDR.set(addr);
    }
}
