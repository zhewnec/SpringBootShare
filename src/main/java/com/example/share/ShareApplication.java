package com.example.share;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.share.mapper")
public class ShareApplication {
//    @Autowired
//    private TopListTimedTasks topListTimedTasks;

    public static void main(String[] args) {
         SpringApplication.run(ShareApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("init...");
//        topListTimedTasks.updateTopListDate();
    }

}
