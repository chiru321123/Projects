package com.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.bean.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
Register findByEmailId(String emailId);
}
