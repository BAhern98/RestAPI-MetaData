package net.codejava.song.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.song.model.UserDto;
import net.codejava.song.service.Token;
import net.codejava.song.service.UserService;

@RestController
@RequestMapping("/codechallenge")
public class UserController {
	
	@Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");

        if (userService.verifyUser(email, password)) {
            userService.sendVerificationCode(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyUser(@RequestParam String email, @RequestParam String password, @RequestParam String code) {
//        if (userService.verifyUser(email, password, code)) {
//            String accessToken = userService.generateAccessToken(email);
//            return ResponseEntity.ok(accessToken);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid verification code");
//        }
//    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody Map<String,String> requestBody) {
    	String email = requestBody.get("email");
        String password = requestBody.get("password");
        String code = requestBody.get("code");
        
		if (userService.verifyUser(email, password, code)) {
			String accessToken = Token.generateAccessToken();
			return ResponseEntity.ok(accessToken);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid verification code");
		}
    }



}
