package algorithm;

/**
 * 计算1到N中各个数字出现的次数
 */
public class CountNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boom(100);
		smooth(100);
	}

	/**
	 * 暴力 重复计算了
	 */
	public static void boom(int n) {
		int[] num = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 1; i <= n; i++) {
			int j = i;
			for (; j > 0; j /= 10) {
				int temp = j % 10;
				switch (temp) {
				case 0:
					num[0]++;
					break;
				case 1:
					num[1]++;
					break;
				case 2:
					num[2]++;
					break;
				case 3:
					num[3]++;
					break;
				case 4:
					num[4]++;
					break;
				case 5:
					num[5]++;
					break;
				case 6:
					num[6]++;
					break;
				case 7:
					num[7]++;
					break;
				case 8:
					num[8]++;
					break;
				case 9:
					num[9]++;
					break;
				default:
					break;
				}
			}
		}

		for (int i = 0; i < num.length; i++) {
			System.out.println(i + " 在1到" + n + "中出现次数:" + num[i]);
		}
	}

	/**
	 * 找规律, 避免重复计算 
	 * 取第i位左边的数字，乘以10的i-1次幂，得到基础值a。
	 * 如果大于num，则结果为a + 10的i-1次幂； 
	 * 如果小于num，则结果为a；
	 * 如果等于num，则取第i位右边数字，设为b，最后结果为a+b+1。
	 * 注意0
	 */
	public static void smooth(int n) {
		int[] num = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int index = 0; index < num.length; index++) {
			int count = 0;
			int k;
			for (int i = 1; (k = n / i) > 0; i *= 10) {

				int high = k / 10;
				if (index == 0) {
					if (high > 0) {
						high = high - 1;
					} else {
						break;
					}
				}

				count = count + high * i;
				int nowNum = k % 10;
				if (nowNum > index) {
					count = count + i;
				} else if (nowNum == index) {
					count = count + n - k * i + 1;
				}

			}

			num[index] = count;
		}
		for (int i = 0; i < num.length; i++) {
			System.out.println(i + " 在1到" + n + "中出现次数:" + num[i]);
		}
	}

}
