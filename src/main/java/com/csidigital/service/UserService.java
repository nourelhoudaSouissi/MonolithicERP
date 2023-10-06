package com.csidigital.service;



import com.csidigital.models.User;

import java.util.List;

public interface UserService {
    User CreateUser(User user);
    User UpdateUser(User user);
    User getUserByUsername(String username);
    List<User>AllUser();
    User GetbyId (Long id);
    void DeleteUser(Long id);
}
