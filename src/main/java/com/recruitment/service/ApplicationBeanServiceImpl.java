package com.recruitment.service;

import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitment.bean.ApplicationBean;
import com.recruitment.exception.RecruitmentManagementException;
import com.recruitment.repository.ApplicationBeanRepository;

@Service
public class ApplicationBeanServiceImpl implements ApplicationBeanService{

	@Autowired
	ApplicationBeanRepository applicationrepository;
	
	@Override
	public ApplicationBean addApplication(ApplicationBean app)throws RecruitmentManagementException {
		ApplicationBean applicationbean=applicationrepository.save(app);
		return applicationbean;
	}
	public List<ApplicationBean> retriveAll()throws RecruitmentManagementException{
		List<ApplicationBean> aList=applicationrepository.findAll();
		return aList;
	}
	@Override
	public int updateApplication(String status, String comments, int appId,Date date) throws RecruitmentManagementException{
		
		return applicationrepository.updateApplication(status, comments, appId,date);
	}
	@Override
	public ApplicationBean retriveOne(int id) throws RecruitmentManagementException {
		 return applicationrepository.findOne(id);
	}


}