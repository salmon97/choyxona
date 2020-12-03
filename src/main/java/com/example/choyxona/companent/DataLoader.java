package com.example.choyxona.companent;

import com.example.choyxona.entity.User;
import com.example.choyxona.entity.enums.RoleName;
import com.example.choyxona.repository.RoleRepository;
import com.example.choyxona.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Value("${spring.datasource.initialization-mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            userRepository.save(
                    new User(
                            "salmon",
                            passwordEncoder.encode("123567"),
                            Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_DIRECTOR))
                    )
            );
        }
    }
}
