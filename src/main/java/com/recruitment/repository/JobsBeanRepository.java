package com.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recruitment.bean.JobsBean;


public interface JobsBeanRepository extends JpaRepository< JobsBean ,Integer> {

}
