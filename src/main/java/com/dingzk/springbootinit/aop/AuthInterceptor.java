package com.dingzk.springbootinit.aop;

import com.dingzk.springbootinit.annotation.Authority;
import com.dingzk.springbootinit.common.ErrorCode;
import com.dingzk.springbootinit.exception.BusinessException;
import com.dingzk.springbootinit.model.entity.User;
import com.dingzk.springbootinit.service.UserService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authority)")
    public Object authIntercept(ProceedingJoinPoint joinPoint, Authority authority) throws Throwable {
        User loginUser = userService.getLoginUser();
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        if (authority.role() != 0) {
            if (authority.role() != loginUser.getUserRole()) {
                throw new BusinessException(ErrorCode.NO_AUTH);
            }
        }
        return joinPoint.proceed();
    }
}