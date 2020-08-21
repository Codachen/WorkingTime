package com.worktime.model;

import java.sql.Timestamp;
import java.util.List;
import com.worktime.model.WorkTimeDAO_interface;

public class WorkTimeService {

	private WorkTimeDAO_interface dao;

	public WorkTimeService() {
		dao = new WorkTimeDAO();
	}

	public WorkTimeVO addWorkTime(String workerNo, String jobNo, String jobIntro,
			Timestamp workTimeStart, Timestamp workTimeEnd) {

		WorkTimeVO workTimeVO = new WorkTimeVO();

		workTimeVO.setWorkerNo(workerNo);
		workTimeVO.setJobNo(jobNo);
		workTimeVO.setJobIntro(jobIntro);
		workTimeVO.setWorkTimeStart(workTimeStart);
		workTimeVO.setWorkTimeEnd(workTimeEnd);
	
		dao.insert(workTimeVO);

		return workTimeVO;
	}

	public WorkTimeVO updateWorkTime(String workTimeNo, String workerNo, String jobNo, String jobIntro,
			Timestamp workTimeStart, Timestamp workTimeEnd) {

		WorkTimeVO workTimeVO = new WorkTimeVO();

		workTimeVO.setWorkTimeNo(workTimeNo);
		workTimeVO.setWorkerNo(workerNo);
		workTimeVO.setJobNo(jobNo);
		workTimeVO.setJobIntro(jobIntro);
		workTimeVO.setWorkTimeStart(workTimeStart);
		workTimeVO.setWorkTimeEnd(workTimeEnd);
	
		dao.update(workTimeVO);

		return workTimeVO;
	}

	public void deleteWorkTime(String workTimeNo) {
		dao.delete(workTimeNo);
	}

	public WorkTimeVO getOneWorkTime(String workTimeNo) {
		return dao.findByPrimaryKey(workTimeNo);
	}

	public List<WorkTimeVO> getOneWorker(String workerNo) {
		return dao.getOneWorker(workerNo);
	}
	
	
}