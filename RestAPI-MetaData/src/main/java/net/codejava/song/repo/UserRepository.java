package net.codejava.song.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.song.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
