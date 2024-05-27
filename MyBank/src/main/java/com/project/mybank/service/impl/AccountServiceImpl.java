package com.project.mybank.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mybank.dto.AccountDto;
import com.project.mybank.entity.Account;
import com.project.mybank.mapper.AccountMapper;
import com.project.mybank.repository.AccountRepository;
import com.project.mybank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account=AccountMapper.mapToAccount(accountDto);
		
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist."));
		
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist."));
		
		double total=account.getBalance()+amount;
		account.setBalance(total);
		
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist."));
		
		double total=account.getBalance()-amount;		//can add a "insufficent balance" algorithm too
		account.setBalance(total);
		
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts=accountRepository.findAll();
		
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());	//try to understand
	}

	@Override
	public void deleteAccount(Long id) {
		
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exist."));
		
		accountRepository.deleteById(id);
		
	}

	

}
