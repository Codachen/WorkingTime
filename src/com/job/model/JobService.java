package com.job.model;

import java.util.List;
import java.util.Set;

public class JobService {

	private JobDAO_interface dao;

	public JobService() {
		dao = new JobDAO();
	}

	public List<JobVO> getAll() {
		return dao.getAll();
	}
}
