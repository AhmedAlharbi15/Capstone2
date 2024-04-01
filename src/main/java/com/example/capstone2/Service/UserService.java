package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void addUser(User user)
    {
        userRepository.save(user);

    }

    public void updateUser(Integer id,User user){
        User user1=userRepository.findUserById(id);

        if(user1 == null){
            throw new ApiException("id not found");
        }
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);

    }
    public void deleteUser(Integer id){
        User user=userRepository.findUserById(id);
        if(user == null){
            throw new ApiException("id not found");
        }
        userRepository.delete(user);
    }

    public User getUserByName(String name){
        User user = userRepository.findUserByName(name);
        if(user==null){
            throw new ApiException("No User in this name");
        }
        return user;
    }

}