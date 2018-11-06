<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
<style>
body {margin:0;}

.navbar {
  overflow: hidden;
  background-color: #333;
  position: fixed;
  top: 0;
  width: 100%;
}
.divbar {
  overflow: hidden;
  position: absolute;
  top: 100pt;
  width: 100%;
}

.navbar a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.navbar a:hover {
  background: #ddd;
  color: black;
}

.main {
  padding: 16px;
  margin-top: 30px;
  height: 1500px; /* Used in this example to enable scrolling */
}
strong {
	 padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}
.divbar2{
  overflow: hidden;
  position: absolute;
  top: 200pt;
  width: 100%;
}
</style>
</head>

<body bgcolor="#00FFEE" align="center" >

<div class="navbar">
 <!--  <a href="#home">Home</a> ?id=${userid} --><h1><font color="#ddd">Welcome to the login page</font></h1>
  <!-- <a href="#news">News</a> -->
  <a href="Apply" >Apply for job</a>
  <a href="viewApplications">view Applied</a><%-- 
  <a href="status?id=${userid}">view Status</a> --%>
  <a href="viewProfile"><strong>Welcome:<font color="red"><abbr title="viewProfile">${name}</abbr></font></strong></a><c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				Welcome ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>

		</c:if>
</div>
${check}	<div class="divbar">
		
<br>
<h3>${userId}</h3>
				<br><br>
				${msg}</div>
				<div class="divbar2">
				
			<c:choose>
				<c:when test="${check}">
				<table width="100%">
				<tr><th>job title</th><th>organization Name</th><th>Vaccency</th><th>salary</th><th>Start Date</th>
				<th>Last Date</th><th>Qualification</th><th>Experience</th></tr>
				<c:forEach items="${joblist}" var="job">
				<tr>
				<td>${job.getJobTitle()}</td>
				<td>${job.getOrganizationName()}</td>
				<td>${job.getNumberOfVaccency()}</td>
				<td>${job.getJobSalary()}</td>
				<td>${job.getStartDate()}</td>
				<td>${job.getLastDate()}</td>
				<td>${job.getQualification()}</td>
				<td>${job.getExperience()}</td>
				</tr>
				</c:forEach>
								
				</table>
					</c:when>
				<c:otherwise>
				<h1>No Jobs to display</h1>
				</c:otherwise>
			</c:choose>
				
				</div>
</body>
</html>