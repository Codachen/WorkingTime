package com.worker.model;

import java.util.*;

import com.job.model.JobVO;

public interface WorkerDAO_interface {
	
          public WorkerVO findByIDPW(String workerID,String workerPW);
	      public List<WorkerVO> getAll();   

      
}

