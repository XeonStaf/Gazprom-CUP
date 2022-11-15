package com.example.gazpromcup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public abstract class GazpromCupApplicationTests {
    protected final String userId = "152855497";
    protected final String groupId = "itmostudents";

    @Value("${vk-token}")
    protected String token;


}
