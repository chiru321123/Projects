package com.recruitment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recruitment.bean.Register;
import com.recruitment.exception.RecruitmentManagementException;
import com.recruitment.repository.RegisterRepository;
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterRepository registerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Register AddRegister(Register registerUser) throws RecruitmentManagementException{
		registerUser.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
		Register register=registerRepository.save(registerUser);
		return register;
	}

	@Override
	public Register retiveOne(int userId) throws RecruitmentManagementException {
		Register register=registerRepository.findOne(userId);
		return register;
	}

	@Override
	public List<Register> RetriveAll() throws RecruitmentManagementException{

		List<Register> registerList=registerRepository.findAll();
		return registerList;
	}

	@Override
	public Register findByEmailId(String emailId) throws RecruitmentManagementException {
		// TODO Auto-generated method stub
		return registerRepository.findByEmailId(emailId);
	}
 
	
}
