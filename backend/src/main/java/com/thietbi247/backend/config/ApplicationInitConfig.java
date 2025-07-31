package com.thietbi247.backend.config;

import com.thietbi247.backend.entity.User;
import com.thietbi247.backend.enums.Roles;
import com.thietbi247.backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository repository){
        return args -> {
            var  roles = new HashSet<String>();
            if(repository.findByUserName("admin").isEmpty()){
                roles.add(Roles.ADMIN.name());

                User user = User.builder()
                        .email("admin@gmail.com")
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                repository.save(user);
                log.info("Admin has been saved");
            }
        };
    }
}
