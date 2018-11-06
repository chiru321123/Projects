package com.recruitment.service;

import java.util.List;

import com.recruitment.bean.Register;
import com.recruitment.exception.RecruitmentManagementException;

public interface RegisterService {
	public Register findByEmailId(String emailId) throws RecruitmentManagementException;
	public Register AddRegister(Register registerUser) throws RecruitmentManagementException;
	public Register retiveOne(int userId)throws RecruitmentManagementException;
	public List<Register> RetriveAll()throws RecruitmentManagementException;
}
