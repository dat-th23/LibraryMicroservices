package com.library;

import com.library.entity.User;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LibraryServiceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceManagementApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initData(UserService userService, UserRepository userRepository) {
        return args -> {
            List<User> listUserExisted = userRepository.findAll();
            if (listUserExisted.isEmpty()) {
                userService.saveUser(new User("Nguyễn Thành Đạt", "admin", "admin123", "053453455", "dat.th1230@gmail.com", "1.png", "Hà Nội",
                        User.AccountStatus.ACTIVE, 500000, new ArrayList<>()));
                userService.saveUser(new User("Mào Văn Dũng", "dung", "1234", "053453455", "dung@gmail.com", "2.png", "Lào Cai",
                        User.AccountStatus.ACTIVE, 500000, new ArrayList<>()));
                userService.saveUser(new User("Đặng Trần Huy", "huy", "1234", "053453455", "huy@gmail.com", "3.png", "Hà Nội",
                        User.AccountStatus.ACTIVE, 500000, new ArrayList<>()));
                userService.saveUser(new User("Nguyễn Minh Tuấn", "tuan", "1234", "053453455", "tuan@gmail.com", "4.png", "Ninh Bình",
                        User.AccountStatus.ACTIVE, 500000, new ArrayList<>()));
                userService.saveUser(new User("Lê Ngọc Duy", "duy", "1234", "053453455", "duy@gmail.com", "4.png", "Hà Nội",
                        User.AccountStatus.ACTIVE, 500000, new ArrayList<>()));


                userService.addRoleToUser("dat.th1230@gmail.com", "ADMIN");
                userService.addRoleToUser("dung@gmail.com", "USER");
                userService.addRoleToUser("huy@gmail.com", "USER");
                userService.addRoleToUser("tuan@gmail.com", "USER");
                userService.addRoleToUser("duy@gmail.com", "USER");
            }
        };
    }
}
