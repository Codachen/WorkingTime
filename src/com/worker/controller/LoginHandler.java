package com.worker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.worker.model.*;

public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		

		
		HttpSession session = null;

		if ("login".equals(action)) {
			PrintWriter out = res.getWriter();

			// 【取得使用者 帳號(account) 密碼(password)】
			String workerID = req.getParameter("workerID");
			String workerPW = req.getParameter("workerPW");

			WorkerService workerSvc = new WorkerService();
			WorkerVO workerVO =  workerSvc.getWorkerInfo(workerID, workerPW);
			
			req.setAttribute("workerVO", workerVO);
			
			String workerNo = null;
			try {
			workerNo = workerVO.getWorkerNo();//把查詢結果放入workerNo
			

			}catch (Exception e) {
				if (workerNo == null) { //看查詢結果是不是空值
					out.print("<script language='javascript'>alert('帳號或密碼輸入錯誤，請重新輸入!!');window.location.href='login.html';</script>");                    //彈框提示,點選確定後返回登入介面
					return;
				}
			}
			//【帳號 , 密碼有效時, 才做以下工作】
					session = req.getSession();
					session.setAttribute("workerID", workerID); // *工作1: 才在session內做已經登入過的標識
					session.setAttribute("workerVO",workerVO); // *工作2: 把員工編號 姓名 照片放入workerVO物件

					try {
						String locationB = (String) session.getAttribute("locationB");
						if (locationB != null) {
							session.removeAttribute("locationB"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							res.sendRedirect(locationB);
							return;
						}
					} catch (Exception ignored) {
					}

					res.sendRedirect(req.getContextPath() + "/WorkTime/listAllWorkTime.jsp"); // *工作3: (-->如無來源網頁:則重導首頁)
				}
			
		
			
		
		if ("logout".equals(action)) {
			session = req.getSession();
			session.removeAttribute("workerID");
			session.removeAttribute("workerVO");
			
			res.sendRedirect(req.getContextPath() + "/WorkTime/listAllWorkTime.jsp");
		}
	}
}
