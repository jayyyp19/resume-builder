package com.resume.project.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataNotFoundAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User saveEntity(User user) {
        return userRepository.save(user);
    }

    public User updateEntity(User user) {
            User userDto = userRepository.getById(user.getId());
            if(null != userDto){
                return userRepository.save(user);
            }
            return userDto;
    }

    public List<User> getUsers() {
        return userRepository.getAllById();
    }

    public User deleteEntity(Long userId) {
        User user = userRepository.getById(userId);
        if(null != user){
            user.setIsDeleted(true);
            userRepository.save(user);
        }
        return user;
    }

    public User getById(Long userId) {
        return userRepository.getById(userId);
    }
}
