package com.dubbo.back;

import com.jm.business.entity.User;

import java.util.List;

public interface BackService {
    String back(String msg);
    List<User> getUsers();
}
