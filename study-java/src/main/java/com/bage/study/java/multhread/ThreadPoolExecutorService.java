package com.bage.study.java.multhread;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基本线程池的API实现
 * 参考链接：* https://www.cnblogs.com/dolphin0520/p/3932921.html
 * @author bage
 *
 */
public class ThreadPoolExecutorService {

	public static void main(String[] args) throws InterruptedException {
		
		int corePoolSize = 2; // 核心线程池大小
		int maximumPoolSize = 5; // 最大线程池大小
		long keepAliveTime = 10; // 线程池中超过corePoolSize数目的空闲线程最大存活时间；可以allowCoreThreadTimeOut(true)使得核心线程有效时间
		TimeUnit unit = TimeUnit.SECONDS; // keepAliveTime时间单位
		BlockingQueue<Runnable> workQueue; // 阻塞任务队列
		ThreadFactory threadFactory; // 新建线程工厂
		RejectedExecutionHandler handler; //  当提交任务数超过maxmumPoolSize+workQueue之和时，任务会交给RejectedExecutionHandler来处理
		
//		workQueue = new LinkedBlockingDeque<Runnable>();
		workQueue = new ArrayBlockingQueue<>(3);
		threadFactory = new ThreadFactory() {
			private AtomicInteger count = new AtomicInteger(-1);
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setName("创建线程--thread--" + count.incrementAndGet());
				System.out.println(Thread.currentThread().getName() + "--->>>" + thread.getName());
				return thread;
			}
		};
		handler = new ThreadPoolExecutor.CallerRunsPolicy();
//		handler = new ThreadPoolExecutor.AbortPolicy();
//		ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
//		ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
//		ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
//		ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 
		// ThreadPoolExecutor
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				corePoolSize , 
				maximumPoolSize , 
				keepAliveTime , 
				unit , 
				workQueue , 
				threadFactory , 
				handler );
		for (int i = 0; i < 20; i++) {
			final int index = i;
			threadPoolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
//						Thread.sleep(1000*3);
						System.out.println(Thread.currentThread().getName() + "--->>>" + " running");
//						System.out.println("加入线程：thread-" + index );
//						System.out.println("当前：workQueue.size():" + workQueue.size());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
//			Thread.sleep(1000);
		}
		try {
			Thread.sleep(10000);
			threadPoolExecutor.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// 
		// threadPoolExecutor.prestartCoreThread();
		
//		SynchronousQueue,
//		LinkedBlockingDeque,
//		ArrayBlockingQueue;

//		threadPoolExecutor.shutdownNow();
	}
	
}
