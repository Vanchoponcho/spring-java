package com.example.testsprng;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppProfile {
    @Value("${app.profile}")
    private String appProfile;
}
