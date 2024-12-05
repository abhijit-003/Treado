package com.codex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codex.domain.TwoFactorOTP;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOTP,String >{
	TwoFactorOTP findByUserId(Long userId); 
}
