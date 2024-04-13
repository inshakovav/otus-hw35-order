package com.example.order;

import com.example.order.entity.UserEntity;
import com.example.order.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@Slf4j
@ActiveProfiles("local")
class OrderApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setFirstName("fistname");
        user.setUsername("username");

        List<UserEntity> userEntities = userRepository.findAll();
        log.info("Read users={}", userEntities);

        userRepository.save(user);
        log.info("New user saved");

        List<UserEntity> userEntities1 = userRepository.findAll();
        log.info("Read users={}", userEntities1);
    }

}
