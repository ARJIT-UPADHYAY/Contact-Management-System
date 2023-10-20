package com.contact.management.system.repository;

import com.contact.management.system.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
