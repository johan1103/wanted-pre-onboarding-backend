package com.wanted.wantedlab.repository;

import com.wanted.wantedlab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
