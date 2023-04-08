package net.codejava.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.codejava.song.model.User;
import net.codejava.song.model.UserDto;
import net.codejava.song.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final VerificationCodeGenerator codeGenerator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, VerificationCodeGenerator codeGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.codeGenerator = codeGenerator;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getEmail());
        return userRepository.save(user);
    }
  

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean verifyUser(String username, String password) {
        User user = findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean verifyUser(String username, String password, String code) {
        User user = findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user.getVerificationCode().equals(code);
        }
        return false;
    }

    @Override
    public void sendVerificationCode(String username) {
        User user = findByUsername(username);
        if (user != null) {
            String code = codeGenerator.generateCode();
            user.setVerificationCode(code);
            userRepository.save(user);
            emailService.sendVerificationCode(user.getEmail(), code);
        }
    }
    
}
