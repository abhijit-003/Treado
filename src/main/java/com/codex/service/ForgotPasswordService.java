package com.codex.service;

import com.codex.domain.VerificationType;
import com.codex.modal.ForgotPasswordToken;
import com.codex.modal.User;

public interface ForgotPasswordService {

	ForgotPasswordToken createToken(
			User user,
			String id,
			String otp,
			VerificationType verificationType,
			String sendTo);
	
	ForgotPasswordToken findById(String id);
	
	ForgotPasswordToken findByUser(Long userId);
	
	void deleteToken(ForgotPasswordToken token);
}
