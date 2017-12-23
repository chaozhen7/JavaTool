package enhanceTheadpool;

import java.util.concurrent.TimeUnit;

public class TestRun {

	private static final int CORE_SIZE = 5;

	private static final int MAX_SIZE = 10;

	private static final long KEEP_ALIVE_TIME = 30;

	private static final int QUEUE_SIZE = 5;

	static EnhancedThreadPoolExecutor executor = new EnhancedThreadPoolExecutor(CORE_SIZE, MAX_SIZE, KEEP_ALIVE_TIME,
			TimeUnit.SECONDS, new TaskQueue(QUEUE_SIZE));

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 15; i++) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			System.out.println("当前线程池中线程数:" + executor.getPoolSize() + " ，队列中任务数：" + executor.getQueue().size());
		}
		
		executor.shutdown();
	}

}
