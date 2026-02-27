package com.beeshop.sd44.service;

import com.beeshop.sd44.entity.User;
import com.beeshop.sd44.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getByEmail(String email) {
        Optional<User> user = userRepo.getUserByEmail(email);
        if(user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User createUser(User user) {
       return this.userRepo.save(user);
    }

    public Boolean isUserExit(String email, String phone) {
        return this.userRepo.existsByEmailOrPhone(email, phone);
    }

}
