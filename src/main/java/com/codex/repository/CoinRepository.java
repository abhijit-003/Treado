package com.codex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codex.modal.Coin;

public interface CoinRepository extends JpaRepository<Coin, String>{

}
