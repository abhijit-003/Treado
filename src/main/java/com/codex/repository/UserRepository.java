//we will manage our data in databse using this class

package com.codex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codex.modal.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
