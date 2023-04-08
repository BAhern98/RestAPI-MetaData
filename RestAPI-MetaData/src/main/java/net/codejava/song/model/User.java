package net.codejava.song.model;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;

import jakarta.persistence.*;

//@Entity
//@Table(name = "user")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "username", unique = true, nullable = false, length = 50)
//    private String username;
//
//    @Column(name = "password", nullable = false, length = 255)
//    private String password;
//
//    // default constructor for JPA
//    protected User() {}
//
//    // constructor with arguments
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
// 
//}

@Entity
@Table(name = "user")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(unique = true)
    private String email;
 
    private String username;

    private String password;
 
    private boolean enabled;
 
    private String secret;
    
    private String verificationCode;
 
    // Constructor, getters and setters
 
    // 2 factor authentication
    public String getVerificationCode() {
        return verificationCode;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}

	public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
    public boolean isTwoFactorAuthenticationEnabled() {
        return secret != null && !secret.trim().isEmpty();
    }
 
    public void enableTwoFactorAuthentication() {
        secret = generateSecret();
    }
 
    public void disableTwoFactorAuthentication() {
        secret = null;
    }
 
    private String generateSecret() {
        byte[] buffer = new byte[10];
        new SecureRandom().nextBytes(buffer);
        Base32 base32 = new Base32();
        return new String(base32.encode(buffer));
    }

 
    public String getQRBarcodeURL(String email, String secret) {
        String format = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s";
        return String.format(format, email, "example.com", secret);
    }
 
    public String getQRBarcode() {
        return getQRBarcodeURL(email, secret);
    }
    

    public User() {
    }
    public User(String username, String password, String email) {
        this.email = email;
        this.password = password;
        this.enabled = true;
        // Generate the secret key for 2 factor authentication
        enableTwoFactorAuthentication();
    }
 
    public boolean verifyTwoFactorAuthenticationCode(String code) {
        long timeStep = 30 * 1000; // 30 seconds
        long time = System.currentTimeMillis();
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
 
        // convert Unix time to "Windows time"
        long unixTime = (time / 1000L) - 2208988800L;
        long counter = unixTime / timeStep;
 
        try {
            // Hash the counter and secret together
            byte[] key = Arrays.copyOf(decodedKey, decodedKey.length + 8);
            for (int i = decodedKey.length; i < key.length; ++i) {
                key[i] = 0;
            }
            ByteBuffer buffer = ByteBuffer.allocate(8).putLong(counter);
            byte[] timeBytes = buffer.array();
            Mac hmac = Mac.getInstance("HMACSHA1");
            hmac.init(new SecretKeySpec(key, "HMACSHA1"));
            byte[] hash = hmac.doFinal(timeBytes);
 
            // Take the last 4 bits of the hash and use it as an offset
            int offset = hash[hash.length - 1] & 0xF;
 
            // Take 4 bytes from the hash, starting from the offset
            int binary =
                ((hash[offset] & 0x7f) << 24) |
                ((hash[offset + 1] & 0xff) << 16) |
                ((hash[offset + 2] & 0xff) << 8) |
                (hash[offset + 3] & 0xff);
 
            // Modulo with 1 million to get a 6 digit code
            int codeInt = binary % 1000000;
 
            return codeInt == Integer.parseInt(code);
        } catch (Exception ex) {
            return false;
        }
    }
}

