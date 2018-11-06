package com.recruitment.service;

import java.sql.Date;
import java.util.List;

import com.recruitment.bean.ApplicationBean;
import com.recruitment.exception.RecruitmentManagementException;

public interface ApplicationBeanService {
	public ApplicationBean addApplication(ApplicationBean app)throws RecruitmentManagementException;
	public List<ApplicationBean> retriveAll()throws RecruitmentManagementException;
	public ApplicationBean retriveOne(int id) throws RecruitmentManagementException;
	public int updateApplication(String status,String comments,int appId,Date date)throws RecruitmentManagementException;

}
