package com.codex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codex.config.JwtProvider;
import com.codex.domain.TwoFactorOTP;
import com.codex.modal.User;
import com.codex.repository.UserRepository;
import com.codex.response.AuthResponse;
import com.codex.service.CustomeUserDetailsService;
import com.codex.service.EmailService;
import com.codex.service.TwoFactorOtpService;
import com.codex.utils.OtpUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private TwoFactorOtpService twoFactorOtpService;
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception{
		
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		
		if(isEmailExist != null) {
			throw new Exception("Email is allread used with another account");
		}
		
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setFullname(user.getFullname());
		User savedUser = userRepository.save(newUser);
		
		
		Authentication auth = new UsernamePasswordAuthenticationToken(
				user.getEmail(),
				user.getPassword()
		);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		
		String jwt = JwtProvider.generateToken(auth);
		
		
		AuthResponse res = new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("register success");
		
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception{
		
		
		String userName = user.getEmail();
		String password = user.getPassword();
		
		 
		Authentication auth = authenticate(userName,password);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		
		String jwt = JwtProvider.generateToken(auth);
		
		User authUser=userRepository.findByEmail(userName);
		
		// If Two Factor auth is enabled for user then 
		if(user.getTwoFactorAuth().isEnabled()) {
			AuthResponse res = new AuthResponse();
			res.setMessage("Two Factor Aut is enabled");
			res.setTwoFactorAuthEnabled(true);
			String otp = OtpUtils.generateOtp();
			
			//if two fact otp is allready present then delete it
			TwoFactorOTP oldTwoFactorOtp= twoFactorOtpService.findByUser(authUser.getId());
			if(oldTwoFactorOtp!=null) {
				twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOtp);
			}
			
			//create new two fact otp
			TwoFactorOTP newTwoFactorOtp=twoFactorOtpService.createTwoFactorOtp(authUser, otp, jwt);
			
			
			emailService.sendVerificationOtpEmail(userName, otp); 
			
			res.setSession(newTwoFactorOtp.getId());
			return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
			
		}
		
		
		// If two factor auth is not enabled for user
		AuthResponse res = new AuthResponse();
		res.setJwt(jwt);
		res.setStatus(true);
		res.setMessage("login success");
		
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}

	/* # authenticate(String userName, String password)
	 * # method to check wether user is exist or not in not database;
	 * # if user exist then we will check the password 
	 * # if password is correct then go for next process
	 * # else throw exception
	 * */
	private Authentication authenticate(String userName, String password) {
		
		UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);
		
		if(userDetails == null) {
			throw new BadCredentialsException("invalid username");
		}
		
		if(!password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException("invalid Password!!");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
	}
	
	@PostMapping("/two-factor/otp/{otp}")
	public ResponseEntity<AuthResponse> verifySigningOtp(
			@PathVariable String otp,
			@RequestParam String id) throws Exception{
		
		TwoFactorOTP twoFactorOTP=twoFactorOtpService.findById(id);
		if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP, otp)) {
			 AuthResponse res=new AuthResponse();
			 res.setMessage("Two factor Authetication verified");
			 res.setTwoFactorAuthEnabled(true);
			 res.setJwt(twoFactorOTP.getJwt());
			 return new ResponseEntity<>(res,HttpStatus.OK);
		}
		throw new Exception("Invalid otp!!");
	}
}
