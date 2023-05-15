package com.example.be.repository;

import com.example.be.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select a.* from  account as a where a.username= :email", nativeQuery = true)
    Account findAccountByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "update account set password= :passwordConfirm where account_id= :id", nativeQuery = true)
    void updateAccount(@Param("id") Long id,
                       @Param("passwordConfirm") String passwordConfirm);
}
