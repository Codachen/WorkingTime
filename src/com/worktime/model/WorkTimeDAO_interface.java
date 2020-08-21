package com.worktime.model;

import java.util.List;

import com.worktime.model.WorkTimeVO;


public interface WorkTimeDAO_interface {
	public void insert(WorkTimeVO workTimeVO);
	public void update(WorkTimeVO workTimeVO);
	public void delete(String workTimeNo);
	public WorkTimeVO findByPrimaryKey(String workTimeNo);
//查全部	public List<WorkTimeVO> getAll();
//查單一
	public List<WorkTimeVO> getOneWorker(String workerNo);
	
}
