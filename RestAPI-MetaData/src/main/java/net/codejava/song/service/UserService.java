package net.codejava.song.service;

import net.codejava.song.model.User;
import net.codejava.song.model.UserDto;

public interface UserService {
    User createUser(UserDto userDto);	
    User findByEmail(String email);
    boolean verifyUser(String email, String password);
    boolean verifyUser(String email, String password, String code);
    void sendVerificationCode(String email);
}
