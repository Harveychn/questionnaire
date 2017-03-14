package com.shiro.util;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 郑晓辉 on 2017/3/13.
 * Description:
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
                                      AuthenticationInfo info) {
        String userTel = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(userTel);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(userTel, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
//        boolean matches = super.doCredentialsMatch(token, info);
        boolean matches = (token == info.getCredentials());
        if (matches) {
            passwordRetryCache.remove(userTel);
        }
        return matches;
    }
}
