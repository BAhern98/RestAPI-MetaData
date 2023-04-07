package net.codejava.song.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.song.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
