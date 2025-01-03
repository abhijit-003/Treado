package com.codex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codex.modal.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
	public VerificationCode findByUserId(Long userId);
}
