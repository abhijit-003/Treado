package com.codex.service;

import com.codex.domain.TwoFactorOTP;
import com.codex.modal.User;

public interface TwoFactorOtpService {
	
	TwoFactorOTP createTwoFactorOtp(User user,String otp,String jwt);
	
	TwoFactorOTP findByUser(Long userId);
	
	TwoFactorOTP findById(String id);
	
	boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP,String otp);
	
	void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP);

}
