package com.recruitment.service;

import java.util.List;

import com.recruitment.bean.JobsBean;
import com.recruitment.exception.RecruitmentManagementException;

public interface JobsBeanService {

	public JobsBean addNew(JobsBean jobsBean)throws RecruitmentManagementException;
	public List<JobsBean> retriveAll()throws RecruitmentManagementException;
	public JobsBean retriveOne(int id) throws RecruitmentManagementException;
}
