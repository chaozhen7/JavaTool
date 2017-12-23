package enhanceTheadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class EnhancedThreadPoolExecutor extends ThreadPoolExecutor {

	private final AtomicInteger submittedTaskCount = new AtomicInteger(0);

	public EnhancedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			TaskQueue workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadPoolExecutor.AbortPolicy());
		workQueue.setExecutor(this);
	}

	// 执行完之后计数器减1
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// TODO Auto-generated method stub
		submittedTaskCount.decrementAndGet();
	}

	public int getSubmittedTaskCount() {
		return submittedTaskCount.get();
	}

	// 执行任务前 计数器+1
	@Override
	public void execute(Runnable command) {
		// TODO Auto-generated method stub
		submittedTaskCount.incrementAndGet();
		try {
			super.execute(command);
		} catch (RejectedExecutionException e) {
			// rejec时，可以重试一次，再失败抛出(可考虑丢到另一个线程池执行)
			BlockingQueue<Runnable> taskQueue = super.getQueue();
			if (taskQueue instanceof TaskQueue) {
				final TaskQueue queue = (TaskQueue) taskQueue;
				if (!queue.forceTaskInfoQueue(command)) {
					submittedTaskCount.decrementAndGet();
					throw new RejectedExecutionException("队列满了，重试失败");
				}
			} else {
				// 不是enhancce队列
				submittedTaskCount.decrementAndGet();
				throw e;
			}
		}

	}

}
