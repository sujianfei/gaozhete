package com.feixiang.aspect;

import com.alibaba.fastjson.JSONObject;
import com.feixiang.TimeCount;
import com.feixiang.annotation.RateLimit;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Aspect
public class RateLimitAspect {

    private ConcurrentHashMap<String, TimeCount> hashMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.feixiang.annotation.RateLimit)")
    public void pointCut() {

    }

    @Around("pointCut() && @annotation(rateLimit)")
    public Object around(ProceedingJoinPoint point, RateLimit rateLimit) throws Throwable {
        int limit=rateLimit.value();
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String className = methodSignature.getDeclaringTypeName();
        Method targetMethod = methodSignature.getMethod();

        String methodName = targetMethod.getName();
        String methodFullName=className+"_"+methodName;
        TimeCount timeCount = hashMap.get(methodFullName);


        boolean flag = true;
        if (timeCount == null) {
            hashMap.put(methodFullName, TimeCount.newTimeCount());
        } else {
            if (timeCount.equalsCurrrentTime()) {
                int count = timeCount.getCount();
                count++;
                if (count > limit) {
                    flag = false;
                } else {
                    timeCount.setCount(count);
                }
            } else {
                hashMap.put(methodFullName, TimeCount.newTimeCount());
            }
        }

        Object obj = null;
        try {
            if (flag) {
                obj = point.proceed();
            } else {
                return "服务器忙";
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return obj;


    }


}