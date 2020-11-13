package com.example.demo.entity;

public class UserDetailsFactory {

    private UserDetailsFactory() {
    }

    /**
     * Creates UserDetailsImpl from a user.
     *
     * @param user user model
     * @return UserDetailsImpl
     */
    public static MyUserDetails create(User user) {
        return new MyUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}