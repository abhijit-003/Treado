package com.codex.service;

import com.codex.domain.VerificationType;
import com.codex.modal.User;
import com.codex.modal.VerificationCode;

public interface VerificationCodeService {
	
	VerificationCode sendVerificationCode(User user, VerificationType verificationType);
	
	VerificationCode getVerificationCodeById(Long id) throws Exception;
	
	VerificationCode getVerificationCodeByUser(Long userId);
	
	
	void deleteVerificationCodeById(VerificationCode verificationCode);
}
