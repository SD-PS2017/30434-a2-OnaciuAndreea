package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import main.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
	@Transactional
    void deleteByUsername(String name);
}
