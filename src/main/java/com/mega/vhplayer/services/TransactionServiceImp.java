package com.mega.vhplayer.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mega.vhplayer.mappers.TransactionMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImp implements TransactionService {
	
	private final TransactionMapper tmapper;
	
	@Transactional
	@Override
	public void doInsert(String id, String name) {
		tmapper.insertTest2(name);
		tmapper.insertTest1(id, name);
	}
}
