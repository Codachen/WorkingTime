package com.worker.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.job.model.JobVO;

import java.sql.*;

public class WorkerDAO implements WorkerDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String GET_WORKER_INFO = "SELECT * FROM WORKER where workerID = ? AND workerPW = ?";

	private static final String GET_ALL_STMT = "SELECT * FROM WORKER";

	
	@Override
	public WorkerVO findByIDPW(String workerID,String workerPW){
		WorkerVO workerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_WORKER_INFO);

			pstmt.setString(1, workerID);
			pstmt.setString(2, workerPW);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				workerVO = new WorkerVO();
				workerVO.setWorkerNo(rs.getString("workerNo"));
				workerVO.setWorkerName(rs.getString("workerName"));
				workerVO.setWorkerID(rs.getString("workerID"));
				workerVO.setWorkerPW(rs.getString("workerPW"));
			}

		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return workerVO;
	}

	@Override
	public List<WorkerVO> getAll() {
		List<WorkerVO> list = new ArrayList<WorkerVO>();
		WorkerVO workerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				workerVO = new WorkerVO();
				workerVO.setWorkerNo(rs.getString("workerNo"));
				workerVO.setWorkerName(rs.getString("workerName"));
				workerVO.setWorkerID(rs.getString("workerID"));
				workerVO.setWorkerPW(rs.getString("workerPW"));
				list.add(workerVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	

}
