package com.codex.service;

import com.codex.domain.VerificationType;
import com.codex.modal.User;

public interface UserService{

	public User findUserProfileByJwt(String jwt) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public User findUserById(Long userId) throws Exception;
	public User enableTwoFactorAuhthentication(
			VerificationType verificationType,
			String sendTo,
			User user);
	User updatePassword(User user,String newPassword);
}
