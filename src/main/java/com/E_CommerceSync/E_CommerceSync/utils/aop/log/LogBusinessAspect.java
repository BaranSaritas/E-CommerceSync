package com.E_CommerceSync.E_CommerceSync.utils.aop.log;
import com.E_CommerceSync.E_CommerceSync.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LogBusinessAspect {

    @Around("@annotation(LoggingAspect)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getMethod().getName();
        Instant startTime = Instant.now();
        Object result = joinPoint.proceed();
        Object[] args= joinPoint.getArgs();

        // yeni bir topic yap ve icine datayi Ordera gore ver öyle anlarız calıstıgını cast ettıgımızde sonuc yok ve args bir data vermiyor
        for (Object arg : args) {
            System.out.println("Argument: " + arg);
            // Eğer belirli bir sınıf tipindeyse daha fazla detay yazdırabilirsiniz
            if (arg instanceof Order) {
                Order body = (Order) arg;
                System.out.println("Body Data: " + body.getId() + body.getStatus());
            }
        }
        String additionalMessage = methodSignature.getMethod().getAnnotation(LoggingAspect.class).additionalMessage();
        long elapsedTime = Duration.between(startTime, Instant.now()).toMillis();
        log.info("Class Name: {}, Method Name: {}, Additional Message: {}, Elapsed Time: {}ms",
                className, methodName, additionalMessage, elapsedTime);
      //  log.info("Result: {}", result); listelerde uzun ve mantiksiz ama dönüş tipini veriyor
        return result;
    }
}