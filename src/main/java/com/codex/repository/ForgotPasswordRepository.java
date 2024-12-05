package com.codex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codex.modal.ForgotPasswordToken;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken,String>{
	ForgotPasswordToken findByUserId(Long userId);
}
