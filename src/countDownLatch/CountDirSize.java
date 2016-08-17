package countDownLatch;

import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年8月17日 上午10:24:40
 * @version 1.0
 * @deprecated 使用countDownLatch统计文件目录大小 最后汇总
 */
public class CountDirSize {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			String[] files = {"/Users/sina/Downloads","/Users/sina/Documents"};
			countSize(files);
		}		
	}
	
	public static void countSize(String[] files) throws InterruptedException{
		int length = files.length;
		final List<Double> sizeList = new Vector<>();
		final CountDownLatch cLatch = new CountDownLatch(length);
		long start = System.currentTimeMillis();
		for(final String f:files){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sizeList.add(countBy(new File(f)));
					cLatch.countDown();
				}
			}).start();
		}
		System.out.println("等待各线程统计结果");
		cLatch.await();
		System.out.println("统计结束开始汇总");
		double size = 0.0;
		for(double d:sizeList){
			size += d;
		}
		long end = System.currentTimeMillis();
		System.out.println("统计时间是 "+((end - start)/1000.0));
		System.out.println("最后的大小是 "+size+"MB");
	}
	
	public static double countBy(File file) {
		if (file.exists()) {
			// 如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				double size = 0;
				for (File f : children)
					size += countBy(f);
				return size;
			} else {// 如果是文件则直接返回其大小,以“兆”为单位
				double size = (double) file.length() / 1024 / 1024;
				return size;
			}
		} else {
			System.out.println(file.getAbsolutePath()+"文件或者文件夹不存在，请检查路径是否正确！");
			return 0.0;
		}
	}

}
