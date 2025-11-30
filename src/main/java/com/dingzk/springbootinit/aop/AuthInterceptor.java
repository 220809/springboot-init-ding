package com.dingzk.springbootinit.aop;

import com.dingzk.springbootinit.annotation.CheckPermission;
import com.dingzk.springbootinit.common.ErrorCode;
import com.dingzk.springbootinit.enums.UserRoleEnum;
import com.dingzk.springbootinit.exception.BusinessException;
import com.dingzk.springbootinit.model.entity.User;
import com.dingzk.springbootinit.service.UserService;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
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

    @Around("@annotation(checkPermission)")
    public Object authIntercept(ProceedingJoinPoint joinPoint, CheckPermission checkPermission) throws Throwable {
        User loginUser = userService.getLoginUser();
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        if (loginUser.getUserRole() == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        String allowRole = checkPermission.allow();
        if (StrUtil.isBlank(allowRole)) {
            return joinPoint.proceed();
        }
        if (!allowRole.equals(userRoleEnum.getName())) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        return joinPoint.proceed();
    }
}