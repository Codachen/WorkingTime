package com.worktime.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WorkTimeDAO implements WorkTimeDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO WORKTIME (workTimeNo,workerNo,jobNo,jobIntro,workTimeStart,workTimeEnd) VALUES ('T'||LPAD(to_char(WORKTIME_SEQ.NEXTVAL), 4, '0'), ?, ?, ?, ?, ?)";
	// 查詢(全部)
	private static final String GET_ALL_STMT = 
			"SELECT * FROM WORKTIME order by workTimeNo";
	// 查詢(單筆)
	private static final String GET_ONE_STMT = 
			"SELECT * FROM WORKTIME where workTimeNo = ?";
	// 查詢單會員
	private static final String GET_ONE_WORKER_STMT = "SELECT * FROM  WORKTIME WHERE WORKERNO=? ORDER BY workTimeStart DESC";
	
	private static final String DELETE = 
			"DELETE FROM  WORKTIME where workTimeNo = ?";
	private static final String UPDATE = 
			"UPDATE WORKTIME set workerNo=?, jobNo=?, jobIntro=?, workTimeStart=?, workTimeEnd=? where workTimeNo = ?";

	@Override
	public void insert(WorkTimeVO workTimeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, workTimeVO.getWorkerNo());
			pstmt.setString(2, workTimeVO.getJobNo());
			pstmt.setString(3, workTimeVO.getJobIntro());
			pstmt.setTimestamp(4, workTimeVO.getWorkTimeStart());
			pstmt.setTimestamp(5, workTimeVO.getWorkTimeEnd());
			

			pstmt.executeUpdate();
			
			// Handle any SQL errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
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

				}
	@Override
	public void update(WorkTimeVO workTimeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, workTimeVO.getWorkerNo());
			pstmt.setString(2, workTimeVO.getJobNo());
			pstmt.setString(3, workTimeVO.getJobIntro());
			pstmt.setTimestamp(4, workTimeVO.getWorkTimeStart());
			pstmt.setTimestamp(5, workTimeVO.getWorkTimeEnd());
			pstmt.setString(6, workTimeVO.getWorkTimeNo());


			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	
}
	@Override
	public void delete(String workTimeNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, workTimeNo);

			pstmt.executeUpdate();
			
			// Handle any driver errors
					} catch (SQLException se) {
						throw new RuntimeException("A database error occured. "
								+ se.getMessage());
						// Clean up JDBC resources
					} finally {
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
	}
		@Override
		public WorkTimeVO findByPrimaryKey(String workTimeNo) {

			WorkTimeVO workTimeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, workTimeNo);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					workTimeVO = new WorkTimeVO();
					workTimeVO.setWorkTimeNo(rs.getString("workTimeNo"));
					workTimeVO.setWorkerNo(rs.getString("workerNo"));
					workTimeVO.setJobNo(rs.getString("jobNo"));
					workTimeVO.setJobIntro(rs.getString("jobIntro"));
					workTimeVO.setWorkTimeStart(rs.getTimestamp("workTimeStart"));
					workTimeVO.setWorkTimeEnd(rs.getTimestamp("workTimeEnd"));
					
				}
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return workTimeVO;
		}
		
		
		
		@Override
		public List<WorkTimeVO> getOneWorker(String workerNo) {
			List<WorkTimeVO> list = new ArrayList<WorkTimeVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			WorkTimeVO workTimeVO = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_WORKER_STMT);
				pstmt.setString(1, workerNo);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					workTimeVO = new WorkTimeVO();
					workTimeVO.setWorkTimeNo(rs.getString("workTimeNo"));
					workTimeVO.setWorkerNo(rs.getString("workerNo"));
					workTimeVO.setJobNo(rs.getString("jobNo"));
					workTimeVO.setJobIntro(rs.getString("jobIntro"));
					workTimeVO.setWorkTimeStart(rs.getTimestamp("workTimeStart"));
					workTimeVO.setWorkTimeEnd(rs.getTimestamp("workTimeEnd"));
					list.add(workTimeVO); // Store the row in the list
				}
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return list;
		}
		
	
		
	}
	
	