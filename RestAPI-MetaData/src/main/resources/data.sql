-- Create Track table
CREATE TABLE Track (
    id INT PRIMARY KEY AUTO_INCREMENT,
    isrc VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    duration_ms INT NOT NULL,
    explicit BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO Track (isrc, name, duration_ms, explicit) VALUES
('TEST1234560000', 'Some new track!', 12000000, true),
('TEST1234560001', 'Another track', 18000000, false),
('TEST1234560002', 'Third track', 15000000, true);

-- Create User table
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Insert sample data
INSERT INTO User (username, password) VALUES
('user1', '$2a$10$yTbOQ2mqKRXFw3q3lWAVOukfKjzKrLmdsR1GzOcYjld.kzCt.ZJlC'), -- password: password1
('user2', '$2a$10$jW8rYmtLYT.2D.DwzznTOeWqAzoILOM/w.EGUDsRg.BJW8IkdwYpi'); -- password: password2