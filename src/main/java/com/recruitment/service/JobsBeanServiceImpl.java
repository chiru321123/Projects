package com.recruitment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruitment.bean.JobsBean;
import com.recruitment.exception.RecruitmentManagementException;
import com.recruitment.repository.JobsBeanRepository;
@Service
public class JobsBeanServiceImpl implements JobsBeanService {
	@Autowired
	JobsBeanRepository jobsBeanRepository;
	@Override
	public JobsBean addNew(JobsBean jobsBean) throws RecruitmentManagementException{
		JobsBean job=jobsBeanRepository.save(jobsBean);
		return job;
	}

	@Override
	public List<JobsBean> retriveAll()throws RecruitmentManagementException {
		List<JobsBean> jobList=jobsBeanRepository.findAll();
		return jobList;
	}

	@Override
	public JobsBean retriveOne(int id) throws RecruitmentManagementException {
		JobsBean job=jobsBeanRepository.findOne(id);
		return job;
	}

}
