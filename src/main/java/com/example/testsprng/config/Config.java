package com.example.testsprng.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@ComponentScan(basePackages = "com.example.testsprng")
@PropertySource(value = "classpath:application.properties")
public class Config {
    @Value("${contacts.file.path}")
    private String contactsFilePath;
}
