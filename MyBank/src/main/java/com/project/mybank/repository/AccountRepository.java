package com.project.mybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.mybank.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
