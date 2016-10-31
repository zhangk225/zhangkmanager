package com.zhangkssh.baseframe.common.util.job;

import java.util.HashMap;
import java.util.Map;

import com.zhangkssh.baseframe.common.util.threadlocal.ZhangkRunnable;
import com.zhangkssh.baseframe.common.util.threadlocal.ZhangkThreadLocalManager;

class JobPool {

	private static JobPool jobPool;

	private Map<String, JobBean> jobMap = new HashMap<String, JobBean>();

	private JobPool() {

	}

	static JobPool getInstance() {
		if (jobPool == null) {
			jobPool = new JobPool();
		}
		return jobPool;
	}

	void startJob(final JobInterface jobInterface, final JobBean jobBean) {
		ZhangkThreadLocalManager zhangkThreadLocalManager = new ZhangkThreadLocalManager();
		zhangkThreadLocalManager.putThread(new ZhangkRunnable() {
			@Override
			public void run() {
				jobBean.setResult(JobBean.JOB_DOING);
				String jobId = jobBean.getJobId();
				jobMap.put(jobId, jobBean);
				JobBean jb = jobInterface.doJob(jobBean);
				jobBean.setMessage(jb.getMessage());
				jobBean.setResult(jb.getResult());
				jobMap.put(jobId, jobBean);
			}
		});
	}

	JobBean getJob(String jobId) {
		return jobMap.get(jobId);
	}

	void finishJob(String jobId) {
		jobMap.put(jobId, null);
	}

}
