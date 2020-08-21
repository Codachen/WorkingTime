package com.worker.model;

public class WorkerVO implements java.io.Serializable{
	private String workerNo;
	private String workerName;
	private String workerID;
	private String workerPW;
	
	public String getWorkerNo() {
		return workerNo;
	}
	public void setWorkerNo(String workerNo) {
		this.workerNo = workerNo;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerID() {
		return workerID;
	}
	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}
	public String getWorkerPW() {
		return workerPW;
	}
	public void setWorkerPW(String workerPW) {
		this.workerPW = workerPW;
	}
	
}
