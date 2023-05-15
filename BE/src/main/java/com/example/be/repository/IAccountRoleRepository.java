package com.example.be.repository;

import com.example.be.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRoleRepository extends JpaRepository<AccountRole, Long> {
}
