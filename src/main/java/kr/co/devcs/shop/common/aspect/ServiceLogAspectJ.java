package kr.co.devcs.shop.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ServiceLogAspectJ {
    @Around(value = "execution(* kr.co.devcs.shop.service.*.*(..)) || execution(* kr.co.devcs.shop.common.config.*.*(..))")
    public Object logMethodParameters(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Method method = signature instanceof MethodSignature ? ((MethodSignature) signature).getMethod() : null;

        if(method != null) {
            Parameter[] parameters = method.getParameters();
            Object[] args = joinPoint.getArgs();

            Map<String, Object> parameterMap = new HashMap<>();

            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                String parameterName = parameter.getName();
                Object parameterValue = args[i];
                parameterMap.put(parameterName, parameterValue);
            }
            System.out.println("\n======================================" + signature + " START======================================");
            System.out.println("Method: " + method.getName());
            System.out.println("Parameters and Values: " + parameterMap);
        }
        Object result = joinPoint.proceed();

        System.out.println("Result : " + result);
        System.out.println("======================================" + signature + " END======================================\n");
        return result;
    }
}
