package com.example.Authentication;

import com.example.DBase.Users;

public interface SecurityService {

    void autologin(String email, String password);

    boolean loginUser(String email, String password);

    Users getAuthenticatedUser();

}