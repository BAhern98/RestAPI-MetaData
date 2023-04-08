package net.codejava.song.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VerificationCodeGenerator {

    private final Random random = new Random();
    private final int CODE_LENGTH = 6;

    public String generateCode() {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int digit = random.nextInt(10);
            code.append(digit);
        }

        return code.toString();
    }
}
