package com.codex.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codex.domain.OrderType;
import com.codex.modal.Order;
import com.codex.modal.User;
import com.codex.modal.Wallet;
import com.codex.repository.WalletRepository;

@Service
public class WalletServiceImpl implements WalletService{

	// JPA repository to handle database operations
	
	@Autowired
	WalletRepository walletRepository;
	
	// get specific user wallet
	@Override
	public Wallet getUserWallet(User user) {
		Wallet wallet=walletRepository.findByUserId(user.getId());
		if(wallet == null) {
			wallet = new Wallet();
			wallet.setUser(user);
		}
		return wallet;
	}

	@Override
	public Wallet addBalance(Wallet wallet, long money) {
		BigDecimal balance=wallet.getBalance();
		BigDecimal newBalance=balance.add(BigDecimal.valueOf(money));
		wallet.setBalance(newBalance);
		return walletRepository.save(wallet);
	}

	/*
	 * find wallet by id
	 * to handle empty wallet or not present wallet use Optional class
	 * */
	@Override
	public Wallet findWalletById(Long id) throws Exception {
		Optional<Wallet> wallet=walletRepository.findById(id);
		if(wallet.isPresent()) {
			return wallet.get();
		}
		throw new Exception("Wallet not found!!");
	}

	/*Transfer money from wallet to another wallet
	 * First get receivers wallet
	 * Then check the sufficient balance
	 * then transfer balance
	 * */
	@Override
	public Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception {
		//get user
		Wallet senderWallet=getUserWallet(sender);
		//check sufficient balance
		if(senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0) {
			throw new Exception("Insufficient balance..!!");
		}
		// subtract balance 
		BigDecimal senderBalance=senderWallet.getBalance().subtract(BigDecimal.valueOf(amount));
		// add balance to sender wallet
		senderWallet.setBalance(senderBalance);
		walletRepository.save(senderWallet);
		// add amount to receivers wallet
		BigDecimal receiverBalance=receiverWallet.getBalance().add(BigDecimal.valueOf(amount));
		receiverWallet.setBalance(receiverBalance);
		walletRepository.save(receiverWallet);
		return senderWallet;
	}

	/*Check the payment type 
	 * either BUY or SELL*/
	@Override
	public Wallet payOrderPayment(Order order, User user) throws Exception {
		Wallet wallet=getUserWallet(user);
		
		if(order.getOrderType().equals(OrderType.BUY)) {
			BigDecimal newBalance=wallet.getBalance().subtract(order.getPrice());
			if(newBalance.compareTo(order.getPrice())<0) {
				throw new Exception("Insufficient Funds for This Transaction..!!!");
			}
			wallet.setBalance(newBalance);
		}
		
		else {
			BigDecimal newBalance=wallet.getBalance().add(order.getPrice());
			wallet.setBalance(newBalance);
		}
		walletRepository.save(wallet);
		return wallet;
	}

}
