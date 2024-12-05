package com.codex.service;

import com.codex.modal.Order;
import com.codex.modal.User;
import com.codex.modal.Wallet;

public interface WalletService {
	
	Wallet getUserWallet (User user);
	
	Wallet addBalance(Wallet wallet, long money);
	
	Wallet findWalletById(Long id) throws Exception;
	
	Wallet walletToWalletTransfer(User sender, Wallet receiver,Long amount)throws Exception;
	
	Wallet payOrderPayment(Order order, User user)throws Exception;
}
