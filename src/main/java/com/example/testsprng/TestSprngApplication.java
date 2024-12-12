package com.example.testsprng;

import com.example.testsprng.config.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TestSprngApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        AppProfile appProfile = context.getBean("appProfile", AppProfile.class);
        if (appProfile.getAppProfile().equals("init")) {
            System.out.println("AppProfile is init. Calling automation initializing default-contacts");
            DataInitializer dataInitializer = context.getBean("dataInitializer", DataInitializer.class);
            dataInitializer.init();
        } else {
            System.out.println("AppProfile is NOT init. Manual entry of contacts");
        }

        UserInterface userInterface = context.getBean("userInterface", UserInterface.class);
        userInterface.run();
        context.close();
        }
    }
