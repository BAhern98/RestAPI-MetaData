package net.codejava.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.song.model.UserDto;
import net.codejava.song.service.UserService;

@RestController
@RequestMapping("/codingchallange")
public class UserController {
	
	@Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestParam String email, @RequestParam String password) {
        if (userService.verifyUser(email, password)) {
            userService.sendVerificationCode(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verifyUser(@RequestParam String email, @RequestParam String password, @RequestParam String code) {
        if (userService.verifyUser(email, password, code)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
