<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add new Job</title>
</head>
<body align="center" bgcolor="#bbb">
<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>

		</c:if>
	<form:form modelAttribute="add" action="adding">
		<table align="left">
			<tr>
				<td>Enter Job Title</td>
				<td><form:input path="JobTitle" placeholder="Enter job title" /></td>
				<td><form:errors path="JobTitle"/></td>
			</tr>
			<tr>
				<td>Required Qualifications</td>
				<td><form:input path="qualification"
						placeholder="Enter Qualification" /></td>
						<td><form:errors path="qualification" /></td>
			</tr>
			<tr>
				<td>select start date of Application</td>
				<td><form:input type="date" path="startDate" /></td>
				<td><form:errors path="startDate"/></td>
			</tr>
			<tr>
				<td>select Last date of Application</td>
				<td><form:input type="date" path="lastDate" /></td>
				<td><form:errors path="lastDate"/></td>
			</tr>
			<tr>
				<td>Required Age Limit</td>
				<td><form:input path="AgeLimit" placeholder="Enter Age limit" /></td>
				<td><form:errors path="AgeLimit"/></td>
			</tr>
			<tr>
				<td>JOb location</td>
				<td><form:input path="jobLocation"
						placeholder="Enter Job Location" /></td>
						<td><form:errors path="jobLocation"/></td>
			</tr>
			<tr>
				<td>organization name</td>
				<td><form:input path="organizationName"
						placeholder="Enter Organization Name" /></td>
						<td><form:errors path="organizationName"/></td>
			</tr>
			<tr>
				<td>JOb Description</td>
				<td><form:input path="jobDescription"
						placeholder="Enter job Description" /></td>
						<td><form:errors path="jobDescription"/></td>
			</tr>
			<tr>
				<td>experience required</td>
				<td><form:input path="experience"
						placeholder="Enter experience" /></td>
						<td><form:errors path="experience"/></td>
			</tr>
			<tr>
				<td>Job Salary</td>
				<td><form:input path="jobSalary" placeholder="Enter salary" /></td>
				<td><form:errors path=""/></td>
			</tr>
			<tr>
				<td>Enter Number of vaccency</td>
				<td><form:input path="numberOfVaccency"
						placeholder="Enter number of vaccency" /></td>
						<td><form:errors path="numberOfVaccency"/></td>
			</tr>
			 <tr><td></td>
				<td><input type="submit" value="Add New Job" /></td>
				</tr>
			
		</table>
	</form:form>
</body>
</html>

		<%-- 		<td><form:errors path=""/></td> --%>