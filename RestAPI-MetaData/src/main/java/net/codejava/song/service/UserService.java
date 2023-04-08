package net.codejava.song.service;

import net.codejava.song.model.User;
import net.codejava.song.model.UserDto;

public interface UserService {
    User createUser(UserDto userDto);	
    User findByUsername(String username);
    boolean verifyUser(String username, String password);
    boolean verifyUser(String username, String password, String code);
    void sendVerificationCode(String username);
}
