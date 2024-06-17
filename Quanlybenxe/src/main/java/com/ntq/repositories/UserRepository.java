package com.ntq.repositories;

import com.ntq.pojo.User;

public interface UserRepository {

    User getUserByUsername(String username);

    void addUser(User user);

    boolean authUser(String username, String password);
}
