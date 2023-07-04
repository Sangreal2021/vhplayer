package com.mega.vhplayer.services;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

	public void doInsert(String id, String name);
}
