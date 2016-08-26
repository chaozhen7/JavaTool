package random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sina Email:chaozhen7@163.com
 * @date 2016年8月26日 上午11:16:30
 * @version 1.0
 */
public class RandomRedPacket {

	private int leftMoney;
	private int leftNum;
	private Random rnd;

	public RandomRedPacket(int total, int num) {
		this.leftMoney = total;
		this.leftNum = num;
		this.rnd = new Random();
	}

	public synchronized int nextMoney() {
		if (this.leftNum <= 0) {
			throw new IllegalStateException("抢光了");
		}
		if (this.leftNum == 1) {
			this.leftNum--;
			return this.leftMoney;
		}
		
		double max = this.leftMoney / this.leftNum * 2d;
		int money = (int) (rnd.nextDouble() * max);
		money = Math.max(1, money);
		this.leftMoney -= money;
		this.leftNum--;

		return money;
	}

	public static void main(String[] args) {
		// 1000分－－10元，10个红包
		RandomRedPacket redPacket = new RandomRedPacket(1000, 10);
		List<Double> redLists = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			redLists.add(redPacket.nextMoney() / 100.0d);
		}
		double sum = 0.0;
		for (int i = 0; i < 10; i++) {
			sum += redLists.get(i);
			System.out.println(redLists.get(i)+" 元");
		}
		System.out.println(sum);
	}
}
