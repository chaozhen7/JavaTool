package enhanceTheadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;

public class TaskQueue extends LinkedBlockingQueue<Runnable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EnhancedThreadPoolExecutor executor;

	public TaskQueue(int capacity) {
		super(capacity);
	}

	public void setExecutor(EnhancedThreadPoolExecutor executor) {
		this.executor = executor;
	}

	public boolean forceTaskInfoQueue(Runnable runnable) {
		if (executor.isShutdown()) {
			throw new RejectedExecutionException("线程池已经关闭，添加task到队列失败");
		}
		return super.offer(runnable);
	}
	// 重写规则
	@Override
	public boolean offer(Runnable e) {
		// TODO Auto-generated method stub
		int currentPoolThreadSize = executor.getPoolSize();
		// 线程数达到最大，添加到队列
		if (currentPoolThreadSize == executor.getMaximumPoolSize()) {
			return super.offer(e);
		}
		// 有空闲线程，直接添加到队列
		if (executor.getSubmittedTaskCount() < currentPoolThreadSize) {
			return super.offer(e);
		}
		// 当前线程池数还不是最大，创建线程
		if (currentPoolThreadSize < executor.getMaximumPoolSize()) {
			return false;
		}

		return super.offer(e);
	}

}
