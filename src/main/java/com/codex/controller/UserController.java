package com.codex.controller;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codex.domain.VerificationType;
import com.codex.modal.ForgotPasswordToken;
import com.codex.modal.User;
import com.codex.modal.VerificationCode;
import com.codex.response.ApiResponse;
import com.codex.response.AuthResponse;
import com.codex.service.EmailService;
import com.codex.service.ForgotPasswordService;
import com.codex.service.UserService;
import com.codex.service.VerificationCodeService;
import com.codex.utils.OtpUtils;
import com.codex.request.*;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;
	
	//get user by jwt
	@GetMapping("/api/users/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserProfileByJwt(jwt);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PostMapping("/api/users/verification/{verificationType}/send-otp")
	public ResponseEntity<String> sendVerificationOtp(
			@RequestHeader("Authorization") String jwt,
			@PathVariable VerificationType verificationType ) throws Exception{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());
		
		if(verificationCode==null) {
			verificationCode = verificationCodeService.sendVerificationCode(user, verificationType);
		}
		
		if(verificationType.equals(VerificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());
		}
		
		return new ResponseEntity<String>("Verification Otp sent successfully..",HttpStatus.OK);
	}
	
	@PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
	public ResponseEntity<User> enableTwoFactorAuthentication(
			@PathVariable String otp,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		
		User user=userService.findUserProfileByJwt(jwt);
		
		
		VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());
		
		String sendTo = verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
				verificationCode.getEmail():verificationCode.getMobile();
		
		boolean isVerified = verificationCode.getOtp().equals(otp);
		
		if(isVerified) {
			User updatedUser=userService.enableTwoFactorAuhthentication(
					verificationCode.getVerificationType(), sendTo, user);
			verificationCodeService.deleteVerificationCodeById(verificationCode);
			return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
		}
		throw new Exception("Wrong otp!!");
	}
	
	// #1 Sending token #2 verify token #3 update our password
	

	@PostMapping("/auth/users/reset-password/send-otp")
	public ResponseEntity<AuthResponse> sendForgotPasswordOtp(
			@RequestBody ForgotPasswordTokenRequest req) throws Exception{
		
		
		
		User user=userService.findUserByEmail(req.getSendTo());
		String otp=OtpUtils.generateOtp();
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		
		ForgotPasswordToken token= forgotPasswordService.findByUser(user.getId());
		
		if(token==null) {
			token= forgotPasswordService.createToken(user, id, otp, req.getVerificationType(), req.getSendTo());
			
		}
		
		
		if(req.getVerificationType().equals(VerificationType.EMAIL)) {
			emailService.sendVerificationOtpEmail(
					user.getEmail(), 
					token.getOtp());
		}
		
		AuthResponse response=new AuthResponse();
		response.setSession(token.getId());
		response.setMessage("Password reset otp sent successfully");
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@PatchMapping("/auth/users/reset-password/verify-otp/")
	public ResponseEntity<ApiResponse> resetPassword(
			@RequestParam String id,
			@RequestBody ResetPasswordRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		
		ForgotPasswordToken forgotPasswordToken = forgotPasswordService.findById(id);
		
		Boolean isVerified=forgotPasswordToken.getOtp().equals(req.getOtp());
		
		if(isVerified) {
			userService.updatePassword(forgotPasswordToken.getUser(), req.getPassword());
			ApiResponse res=new ApiResponse();
			res.setMessage("Password Update Successfully");
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		}
		
		throw new Exception("Wrong otp!!");
	}
	
	
}
