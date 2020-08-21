package com.worker.model;

import java.util.List;

import com.worker.model.WorkerVO;

public class WorkerService {

	private WorkerDAO_interface dao;

	public WorkerService() {
		dao = new WorkerDAO();
	}

	public WorkerVO getWorkerInfo(String workerID ,String workerPW) {
		return dao.findByIDPW(workerID, workerPW);
	}
	
	public List<WorkerVO> getAll() {
		return dao.getAll();
	}
	
}
