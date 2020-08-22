<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.worktime.model.*"%>
<%@ page import="com.worker.model.*"%>

<jsp:useBean id="workerSvc" scope="page" class="com.worker.model.WorkerService" />
<jsp:useBean id="jobSvc" scope="page" class="com.job.model.JobService" />


<%
	WorkerVO workerVO = (WorkerVO) session.getAttribute("workerVO");
	String workerNo = workerVO.getWorkerNo();
    WorkTimeService workTimeSvc = new WorkTimeService();
    List<WorkTimeVO> list = workTimeSvc.getOneWorker(workerNo);
    pageContext.setAttribute("list",list);
%>



<html>
<head>
<title>�Ҧ����u��� </title>

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
	width: 800px;
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
<!-- �ϥΪ̫H���P�n�X -->
<div class="logout">
			 <span>${workerVO.workerName} (${workerVO.workerID}) �z�n~</span>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/login.do">
				<button type="submit">�n�X</button>
				<input type="hidden" name="action" value="logout">
			</FORM>
		</div>

		


<table id="table-1">
	<tr><td>
		 <h3>�u�ɬ����d��</h3>
		 <h4><a href="addWorkTime.jsp">�s�W����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		
		<th>�H���m�W</th>
		<th>�u�@�������O</th>
		<th>�u�@���ػ���</th>
		<th>�u�@�}�l�ɶ�</th>
		<th>�u�@�}�l�ɶ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="workTimeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
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
			<td>${workTimeVO.jobIntro}</td>
			<td>${workTimeVO.workTimeStart}</td>
			<td>${workTimeVO.workTimeEnd}</td> 
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/WorkTime/worktime.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="workTimeNo"  value="${workTimeVO.workTimeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/WorkTime/worktime.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="workTimeNo"  value="${workTimeVO.workTimeNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>