package org.example.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ProfilerAspect {
    private final Profiler profiler = Profiler.getInstance();

    @Around("execution(* *(..)) && !within(org.example.profiler..*)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final Signature signature = joinPoint.getSignature();
        final String currentPackage = signature.getDeclaringType().getPackage().toString().substring(8);
        if (profiler.getPackagePrefix() == null || !currentPackage.startsWith(profiler.getPackagePrefix())) {
            return joinPoint.proceed();
        }

        final long begin = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            final long finish = System.nanoTime();
            profiler.register(signature, finish - begin);
        }
    }
}
