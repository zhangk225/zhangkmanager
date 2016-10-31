package com.zhangkssh.baseframe.common.util.job;


public class JobService {

	private static JobPool jobPool = JobPool.getInstance();

	/**
	 * 开始一个任务
	 * 
	 * @param jobInterface
	 * @param jobBean
	 * @param service
	 */
	public void startJob(JobInterface jobInterface, JobBean jobBean) {
		jobPool.startJob(jobInterface, jobBean);
	}

	/**
	 * 通过任务主键获取任务信息
	 * 
	 * @param jobId
	 *            任务主键
	 * @return
	 */
	public String getJobMessage(String jobId) {
		JobPool jobPool = JobPool.getInstance();
		JobBean jb = jobPool.getJob(jobId);
		if (jb != null) {
			if (jb.getResult() != JobBean.JOB_DOING) {
				jobPool.finishJob(jobId);
			}
			return jb.getMessage();
		} else {
			return "";
		}
	}
}
