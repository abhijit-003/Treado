package com.codex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codex.modal.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
	
	Wallet findByUserId(Long userId);
}
