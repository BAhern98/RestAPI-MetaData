package net.codejava.song.model;

public class UserDto {
    private Long id;
    private String email;
    private String password;


    // getters and setters
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
}
