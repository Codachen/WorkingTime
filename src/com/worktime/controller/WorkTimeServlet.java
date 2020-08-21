package com.worktime.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.worktime.model.WorkTimeService;
import com.worktime.model.WorkTimeVO;

public class WorkTimeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String workTimeNo = req.getParameter("workTimeNo");
				
				/*************************** 2.開始查詢資料 ****************************************/
				WorkTimeService workTimeSvc = new WorkTimeService();
				WorkTimeVO workTimeVO = workTimeSvc.getOneWorkTime(workTimeNo);
								
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("workTimeVO", workTimeVO); // 資料庫取出的workTimeVO物件,存入req
				String url = "/WorkTime/update_worktime_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/WorkTime/listAllWorkTime.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String workTimeNo = req.getParameter("workTimeNo");
				
				String workerNo = req.getParameter("workerNo");

				String jobNo = req.getParameter("jobNo").trim();
				if (jobNo == null || (jobNo.trim()).length() == 0) {
					errorMsgs.add("●工作項目類別:請勿空白");
				}
				
				String jobIntro = req.getParameter("jobIntro");
				if (jobIntro == null || (jobIntro.trim()).length() == 0) {
					errorMsgs.add("●工作項目說明:請勿空白");
				}	
				
				java.sql.Timestamp workTimeStart = null;
				try {
				workTimeStart = java.sql.Timestamp.valueOf((req.getParameter("workTimeStart") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●工作開始時間:請勿空白");
				}
			
				java.sql.Timestamp workTimeEnd = null;
				try {
				workTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("workTimeEnd") + ":00");
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●工作結束時間:請勿空白");
				}

				WorkTimeVO workTimeVO = new WorkTimeVO();
				workTimeVO.setWorkTimeNo(workTimeNo);
				workTimeVO.setWorkerNo(workerNo);
				workTimeVO.setJobNo(jobNo);
				workTimeVO.setJobIntro(jobIntro);
				workTimeVO.setWorkTimeStart(workTimeStart);
				workTimeVO.setWorkTimeEnd(workTimeEnd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workTimeVO", workTimeVO); // 含有輸入格式錯誤的workTimeVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/WorkTime/update_worktime_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始新增資料***************************************/
				WorkTimeService workTimeSvc = new WorkTimeService();
				workTimeVO = workTimeSvc.updateWorkTime(workTimeNo, workerNo, jobNo, jobIntro, workTimeStart,workTimeEnd);
				
				/***************************3..新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("workTimeVO", workTimeVO); 
				String url = "/WorkTime/listOneWorkTime.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/WorkTime/update_worktime_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String workerNo = req.getParameter("workerNo");

				
				String jobNo = req.getParameter("jobNo").trim();
				if (jobNo == null || (jobNo.trim()).length() == 0) {
					errorMsgs.add("●工作項目類別:請勿空白");
				}
				
				String jobIntro = req.getParameter("jobIntro");
				if (jobIntro == null || (jobIntro.trim()).length() == 0) {
					errorMsgs.add("●工作項目說明:請勿空白");
				}
				
				java.sql.Timestamp workTimeStart = null;
				try {
				workTimeStart = java.sql.Timestamp.valueOf((req.getParameter("workTimeStart") + ":00").trim());
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●工作開始時間:請勿空白");
				}
			
				java.sql.Timestamp workTimeEnd = null;
				try {
				workTimeEnd = java.sql.Timestamp.valueOf(req.getParameter("workTimeEnd") + ":00");
				}catch(IllegalArgumentException e) {
					errorMsgs.add("●工作結束時間:請勿空白");
				}
				
				WorkTimeVO workTimeVO = new WorkTimeVO();
				workTimeVO.setWorkerNo(workerNo);
				workTimeVO.setJobNo(jobNo);
				workTimeVO.setJobIntro(jobIntro);
				workTimeVO.setWorkTimeStart(workTimeStart);
				workTimeVO.setWorkTimeEnd(workTimeEnd);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("workTimeVO", workTimeVO); // 含有輸入格式錯誤的workTimeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/WorkTime/addWorkTime.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				WorkTimeService workTimeSvc = new WorkTimeService();
				workTimeVO = workTimeSvc.addWorkTime(workerNo, jobNo, jobIntro, workTimeStart, workTimeEnd);
				
				/***************************3..新增完成,準備轉交(Send the Success view)***********/
				String url = "/WorkTime/listAllWorkTime.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/WorkTime/addWorkTime.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String workTimeNo = req.getParameter("workTimeNo");
				
				/*************************** 2.開始刪除資料 ***************************************/
				WorkTimeService workTimeSvc = new WorkTimeService();
				workTimeSvc.deleteWorkTime(workTimeNo);
				
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/WorkTime/listAllWorkTime.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/WorkTime/listAllWorkTime.jsp");
				failureView.forward(req, res);

			}
		}
	}
}
