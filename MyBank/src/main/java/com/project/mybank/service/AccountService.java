package com.project.mybank.service;

import java.util.List;

import com.project.mybank.dto.AccountDto;
import com.project.mybank.entity.Account;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);

}
