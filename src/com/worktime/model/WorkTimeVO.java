package com.worktime.model;

import java.sql.Timestamp;

public class WorkTimeVO implements java.io.Serializable{
	private String workTimeNo;
	private String workerNo;
	private String jobNo;
	private String jobIntro;
	private Timestamp workTimeStart;
	private Timestamp workTimeEnd;
	
	
	public String getWorkTimeNo() {
		return workTimeNo;
	}
	public void setWorkTimeNo(String workTimeNo) {
		this.workTimeNo = workTimeNo;
	}
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	public String getJobNo() {
		return jobNo;
	}
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	public String getJobIntro() {
		return jobIntro;
	}
	public void setJobIntro(String jobIntro) {
		this.jobIntro = jobIntro;
	}
	public Timestamp getWorkTimeStart() {
		return workTimeStart;
	}
	public void setWorkTimeStart(Timestamp workTimeStart) {
		this.workTimeStart = workTimeStart;
	}
	public Timestamp getWorkTimeEnd() {
		return workTimeEnd;
	}
	public void setWorkTimeEnd(Timestamp workTimeEnd) {
		this.workTimeEnd = workTimeEnd;
	}
	
}
	