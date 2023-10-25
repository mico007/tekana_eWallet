package com.dev.TekanaeWallet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


// This class is used to get the bean from the application context
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        CONTEXT = context;
    }

    public static Object getBean(String beanName){
        return CONTEXT.getBean(beanName);
    }
}
