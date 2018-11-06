package com.recruitment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recruitment.bean.ApplicationBean;
import com.recruitment.bean.JobsBean;
import com.recruitment.bean.Register;
import com.recruitment.exception.RecruitmentManagementException;
import com.recruitment.service.ApplicationBeanService;
import com.recruitment.service.JobsBeanService;
import com.recruitment.service.RegisterService;
import com.recruitment.service.SecurityService;

@Controller
public class RecruitmentController {
	public static java.util.Date utilDate = new java.util.Date();
	public static java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	public static int count = 0;
	public static  String emailId=null;
	public static Register reg=null;
	@Autowired
	SecurityService securityService;
	@Autowired
	ApplicationBeanService applicationservice;
	@Autowired
	RegisterService registerService;

	@Autowired
	JobsBeanService jobsBeanService;
	

	@RequestMapping(value= {"/","/sample"})
	public String getHome() throws Exception {
		
		if (count == 0) {
			Register reg = new Register("Admin",sqlDate,"B.Tech","Admin1@gmail.com","Admin@123","7382796062","Male","ADMIN");
			Register register = registerService.AddRegister(reg);
			System.err.println(register);
			count++;
			//throw new RecruitmentManagementException(1,"Normal Exception");
		}
		return "index";
	}

	@RequestMapping("/Register")
	public String candidateRegister(Model model) {
		model.addAttribute("register", new Register());
		return "register";
	}

	@RequestMapping("/registering")
	public String registering(@Valid @ModelAttribute("register") Register rege, BindingResult br, Model model) throws RecruitmentManagementException {
		try {
		Register register = null;
		if (br.hasErrors()) {
			return "register";
		}
		//model.addAttribute("name", reg.getCandidateName());
		
			if(registerService.findByEmailId(rege.getEmailId()) != null) {
				
				model.addAttribute("msg","Email Already Exists");
				return "register";
			}
			else {
				
				register = registerService.AddRegister(rege);	
				System.out.println(register);
				return "login";
			}
		} catch (Exception e) {
			//System.err.println("Exception"+e);
			return "register";
		}
		
	}

	@RequestMapping("/Login")
	public String getLoginPage(Model model, String error, String logout) throws RecruitmentManagementException {
		
		/*if (count == 0) 
		{
			Register rege = new Register("Admin",sqlDate,"B.Tech","Admin1@gmail.com","Admin123","7382796062","Male","ADMIN");
			Register register = registerService.AddRegister(rege);
			//System.err.println(register);
			count++; 		
		}*/

		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");

		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "login";

	}

	@RequestMapping("/adminLogging")
	public String getAdmin() {
		return "adminLogging";
	}
	
	@RequestMapping("/logging")
	public String getUserPage(Model model) throws RecruitmentManagementException {
		try {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     emailId = auth.getName();
	      reg=registerService.findByEmailId(emailId);
		List<JobsBean> jobList=jobsBeanService.retriveAll();
		model.addAttribute("name", reg.getCandidateName());/*
		model.addAttribute("userid", Integer.toString(reg.getCandidateId()));*/
		model.addAttribute("joblist",jobList);
		model.addAttribute("check", true);
		if(jobList.size()<=0) {
			model.addAttribute("check", false);
		}
		return "logging";
		}
		catch (RecruitmentManagementException e) {
			throw new RecruitmentManagementException(1, "Exception occured while logging in");
		}
	}
	public static List<String> getList() {
		List<String> aList = (List<String>) new ArrayList<String>();
		aList.add("");
		aList.add("Hindhu");
		aList.add("christain");
		aList.add("Muslim");
		return aList;
	}

	@RequestMapping("/Apply")
	public String getApplication(Model model/*, @RequestParam("id") String id*/) throws RecruitmentManagementException {
		List<JobsBean> jobList=jobsBeanService.retriveAll();
		model.addAttribute("application", new ApplicationBean());
		model.addAttribute("list", getList());
		// String userId=Integer.toString(id);
		model.addAttribute("userId", reg.getCandidateId());
		model.addAttribute("check", true);
		if(jobList.size()<=0) {
			model.addAttribute("check", false);
		}
		List<String> jobs=new ArrayList<String>();
		jobs.add("Select Job");
		//for
		model.addAttribute("jobs", jobs);
		return "apply";
	}

	@RequestMapping(value = "/applying")
	public String addApplicant(Model model,@Valid @ModelAttribute("application") ApplicationBean applicationBean,BindingResult br ) throws RecruitmentManagementException
	{// Register reg = null;
		if(br.hasErrors() ) {
		model.addAttribute("list", getList());
		model.addAttribute("userId", applicationBean.getUserId());
		return"apply";
	}
		else {

			List<JobsBean> jobList=jobsBeanService.retriveAll();
		try {
			//String id=
			applicationBean.setUserId(Integer.toString(reg.getCandidateId()));
			applicationBean.setModifiedDate(sqlDate);
			applicationBean.setStatus("None");
			applicationBean.setComments("No Comments");
			ApplicationBean appbean = applicationservice.addApplication(applicationBean);
			model.addAttribute("msg", "chiru" + appbean.getApplicationId() + " is your application id.");
			model.addAttribute("joblist",jobList);
			model.addAttribute("check", true);
			if(jobList.size()<=0) {
				model.addAttribute("check", false);
			}
			return "logging";
		} catch (Exception e) {
			try {
				
				model.addAttribute("joblist",jobList);
				return "logging";
			} catch (Exception e1) {
				
				model.addAttribute("joblist",jobList);
				return "logging";
			}
		}

		}

	}

	@RequestMapping("/viewapplied")
	public String getApplications(Model model) throws RecruitmentManagementException  {
		List<ApplicationBean> applications = applicationservice.retriveAll();
		List<ApplicationBean> appValid=new ArrayList<ApplicationBean>();
		for(ApplicationBean app : applications) {
			if("None".equals(app.getStatus()))
				appValid.add(app);
		}
		if (applications.size() <= 0||appValid.size()<=0)
		 {
			//model.addAttribute("msg", "No records to display.");
			model.addAttribute("check", false);
			//return "applications";
		 }		
		model.addAttribute("applications", appValid);
		return "applications";
		
	}

	@RequestMapping("/viewProfile")
	public String viewProfile(Model model/*, @RequestParam("id") int id*/) {
		try {
			Register register = registerService.retiveOne(reg.getCandidateId());
			model.addAttribute("profile", register);
		return "viewProfile";
		} catch (Exception e) {
			
			model.addAttribute("msg", "some technical problems");
			return "error";
		}
	}

	@SuppressWarnings("null")
	@RequestMapping("/viewApplications")
	public String applied(Model model/*,@RequestParam("id") String id*/){

	//	Register reg=null;
		try {
			reg = registerService.retiveOne(/*Integer.parseInt(*/reg.getCandidateId());
			List<ApplicationBean> applications=applicationservice.retriveAll();
			List<ApplicationBean> appList=new ArrayList<ApplicationBean>();
			for(ApplicationBean app:applications) 
			{
				if(app.getUserId().equals(Integer.toString(reg.getCandidateId())))
					appList.add(app);
			}	
			if(applications.size()<=0||appList.size()<=0)
				
			{

				//model.addAttribute("msg", "No records to display.");
				model.addAttribute("check", false);
				return "applicationsuser";
			}
			else 
			{
				
					model.addAttribute("applications", appList);
					return "applicationsuser";

			} 
		}catch (Exception e) {
			return "error";
		}
	}
	@RequestMapping("/addnew")
	public String addNewJob(Model model){
		model.addAttribute("add", new JobsBean());
		return "addnew";
	}
	@RequestMapping("/adding")
	public String addNew(@Valid @ModelAttribute("add") JobsBean job, BindingResult br,Model model) throws RecruitmentManagementException {
		if(br.hasErrors()) {
			return "addnew";
		}
		JobsBean jobs=jobsBeanService.addNew(job);
		model.addAttribute("msg", jobs.getJobId()+"  is Job Id");
		return "adminLogging";
	}
	@RequestMapping("/accept")
	public String acceptRequest(Model model,@RequestParam("id") int id) {
		model.addAttribute("msg","Accepted");
		System.out.println("Accept");
		model.addAttribute("appId",id);
		return "changeStatus";
	}
	@RequestMapping("/reject")
	public String rejectRequest(Model model,@RequestParam("id") int id) {
		model.addAttribute("msg","Rejected");
		System.out.println("Reject");
		model.addAttribute("appId",id);
		return"changeStatus";
	}
	
	@RequestMapping("/change")
	public String changeStatus(Model model,@RequestParam("appid") int appId,@RequestParam("Comments") String comments,@RequestParam("status") String status) throws RecruitmentManagementException {
		
			
			int app=applicationservice.updateApplication(status, comments, appId,sqlDate);
			System.out.println(app);
			System.err.println(applicationservice.retriveAll());;
			model.addAttribute("msg", "application number with"+appId+" is "+status);
			return "adminLogging";

	}
	@RequestMapping("/error1")
	public String errorPage(Model model, @RequestParam("type") int type,@RequestParam("status") String status) {
		model.addAttribute("type", type);
		model.addAttribute("status", status);
		return "error1";
	}
	@RequestMapping("/back")
	public String getBack(Model model) throws RecruitmentManagementException {
		//reg=registerService.findByEmailId(emailId);  preetham.p@capgemini.com 
		System.out.println("back Button");
		if("ADMIN".equals(reg.getUserType()))
		return "adminLogging";
		else {
			List<JobsBean> jobList=jobsBeanService.retriveAll();
			if(jobList.size()<=0) {
				model.addAttribute("check", false);
			}
			return "logging";
			}
		
	}
	 @GetMapping(path = "/get/xml", produces = MediaType.APPLICATION_XML_VALUE)
	   public @ResponseBody String getXML() {
	      return "<user><id>12</id><name>John</name></user>";
	   }
}
