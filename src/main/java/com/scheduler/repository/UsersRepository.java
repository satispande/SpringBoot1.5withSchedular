package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
