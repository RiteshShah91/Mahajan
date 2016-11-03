/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }
    
    public static class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
		
        @Override
        public void handleUncaughtException(Throwable thrwbl, Method method, Object... os) {
            LoggerFactory.getLogger(method.getClass()).error("Error occured when sending email : ", os);	
        }
    	
    }
    
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // seems like too many thread here will make gmail return error
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(3000);
        executor.setThreadNamePrefix("EmailExecutor-");
        executor.initialize();
        return executor;
    }
}