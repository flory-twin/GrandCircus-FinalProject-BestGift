package co.grandcircus.bestgift.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.bestgift.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
