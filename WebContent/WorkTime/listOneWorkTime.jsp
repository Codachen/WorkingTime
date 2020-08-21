<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.worktime.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="jobSvc" scope="page" class="com.job.model.JobService" />
<jsp:useBean id="workerSvc" scope="page" class="com.worker.model.WorkerService" />

<%
  WorkTimeVO workTimeVO = (WorkTimeVO) request.getAttribute("workTimeVO"); %>

<html>
<head>
<title>工時紀錄</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<!-- 使用者信息與登出 -->
<div class="logout">
			 <span>${workerVO.workerName} (${workerVO.workerID}) 您好~</span>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/login.do">
				<button type="submit">登出</button>
				<input type="hidden" name="action" value="logout">
			</FORM>
		</div>
<table id="table-1">
	<tr><td>
		 <h3>工時紀錄查詢</h3>
		 <h4><a href="addWorkTime.jsp">新增</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		
		<th>人員姓名</th>
		<th>工作項目類別</th>
		<th>工作項目說明</th>
		<th>工作開始時間</th>
		<th>工作結束時間</th>
	</tr>
	<tr>
		
		<td>
			<c:forEach var="workerVO2" items="${workerSvc.all}">
            <c:if test="${workerVO2.workerNo==workTimeVO.workerNo}">
	                   ${workerVO2.workerName}
            </c:if>
            </c:forEach>
			</td>
		<td>
		<c:forEach var="jobVO" items="${jobSvc.all}">
            <c:if test="${jobVO.jobNo==workTimeVO.jobNo}">
	                   ${jobVO.jobType}
            </c:if>
        </c:forEach>
		</td>
		<td><%=workTimeVO.getJobIntro()%></td>
		<td><%=workTimeVO.getWorkTimeStart()%></td>
		<td><%=workTimeVO.getWorkTimeEnd()%></td>
	</tr>
</table>

</body>
</html>