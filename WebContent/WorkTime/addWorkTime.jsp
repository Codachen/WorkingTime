<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.worktime.model.*"%>
<%@ page import="com.worker.model.*"%>
<jsp:useBean id="jobSvc" scope="page" class="com.job.model.JobService" />

<%
	WorkerVO workerVO = (WorkerVO) session.getAttribute("workerVO");
	WorkTimeVO workTimeVO = (WorkTimeVO) request.getAttribute("workTimeVO");
	String workerNo = workerVO.getWorkerNo();
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>工時紀錄新增 </title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
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
		 <h3>工時紀錄修改 </h3>
		 <h4><a href="listAllWorkTime.jsp">返回紀錄總覽</a></h4>
	</td></tr>
</table>

<h3>紀錄新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="worktime.do" name="form1">
<table>
	
		<!--員工編號 -->
		<input type="hidden" name="workerNo" value="${workerVO.workerNo}"  />
	
	<tr>
		<td>工作項目類別:</td>
		<td>
		<select size="1" name="jobNo">
			<c:forEach var="jobVO" items="${jobSvc.all}">
				<option value="${jobVO.jobNo}" ${(workTimeVO.jobNo==jobVO.jobNo)?'selected':'' } >${jobVO.jobType}</option>
			</c:forEach>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>工作項目說明:</td>
		<td><input type="TEXT" name="jobIntro" size="25"
			 value="<%= (workTimeVO==null)? "" : workTimeVO.getJobIntro()%>" /></td>
	</tr>
	
	<tr>
		<td>工作開始時間:</td>
		<td><input name="workTimeStart" id="starttime" type="text"></td>
	</tr>
	
	<tr>
		<td>工作結束時間:</td>
		<td><input name="workTimeEnd" id="endtime" type="text"></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#starttime').datetimepicker({
        	format : 'Y-m-d H:i',
			onShow : function() {
				//開始時間不可晚於結束時間
				this.setOptions({
					maxDate : $('#endtime').val() ? $(
							'#endtime').val() : false
				})
			},
			timepicker : true,
			step : 60
		});
        
      //不能選擇未來時間
        var somedate1 = new Date();
        $('#starttime').datetimepicker({
            beforeShowDay: function(date) {
          	  if (  date.getYear() <  somedate1.getYear() || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                ) {
                     return [true, ""]
                }
                return [false, ""];
        }});
   
        $('#endtime').datetimepicker(
				{
					format : 'Y-m-d H:i',
					onShow : function() {
						this.setOptions({
							minDate : $('#starttime').val() ? $(
									'#starttime').val() : false
						})
					},
					timepicker : true,
					step : 60
				});
      //不能選擇未來時間
        $('#endtime').datetimepicker({
            beforeShowDay: function(date) {
          	  if (  date.getYear() <  somedate1.getYear() || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
   		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                ) {
                     return [true, ""]
                }
                return [false, ""];
        }});
        
</script>
</html>