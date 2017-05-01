package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findById(Long id);
}
