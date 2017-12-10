package mima;

import java.util.Scanner;

public class RSA {
	public static void main(String[] arg) {
		int p, q, e, n, fn, d; // 其中fn为φ(n) 也就是(p-1)(q-1)
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入p的值:");
		p = sc.nextInt();
		if (!isPrime(p)) {
			System.out.println("p的值不是素数");
			System.exit(1);
		}
		System.out.println("请输入q的值:");
		q = sc.nextInt();
		if (!isPrime(q)) {
			System.out.println("q的值不是素数");
			System.exit(1);
		}
		System.out.println("请输入e的值:");
		e = sc.nextInt();
		fn = (p - 1) * (q - 1);
		if (getGcd(fn, e) != 1) {
			System.out.println("e 和 φ(n)不互素");
			System.exit(1);
		}
		n = p * q;
		// ed ≡ 1 (mod φ(n)) 这个式子等价于 ed - 1 = kφ(n)
		int[] temp = extend_gcd(e, fn);
		d = temp[1];
		int x = 0;
		while (true) {
			System.out.println("请选择操作");
			System.out.println("1 RSA加密");
			System.out.println("2 RSA解密");
			System.out.println("3 退出");
			x = sc.nextInt();
			if (x == 1) {
				System.out.println("请输入明文:");
				int mingwen = sc.nextInt();
				int C = quick(mingwen, e, n);
				System.out.println("加密结果:  " + C);
				System.out.println("\n****************************\n");
			} else if (x == 2) {
				System.out.println("请输入密文:");
				int miwen = sc.nextInt();
				int M = quick(miwen, d, n);
				System.out.println("解密结果:  " + M);
				System.out.println("\n****************************\n");
			} else {
				break;
			}
		}
	}

	/**
	 * 
	 * @param n
	 *            需要判断的数
	 * 
	 * @return true是素数 false不是素数
	 */
	public static boolean isPrime(int n) {
		if (n < 2)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	/**
	 * 扩展的欧几里德算法
	 * 
	 * @param a
	 *            相当于RSA中的e
	 * @param b
	 *            相当于RSA中的φ(n)
	 * @return result[1] 相当于RSA中的d
	 */
	public static int[] extend_gcd(int a, int b) {
		int ans;
		int[] result = new int[3];
		if (b == 0) {
			result[0] = a;
			result[1] = 1;
			result[2] = 0;
			return result;
		}
		int[] temp = extend_gcd(b, a % b);
		ans = temp[0];
		result[0] = ans;
		result[1] = temp[2];
		result[2] = temp[1] - (a / b) * temp[2];
		return result;
	}

	/**
	 * 快速取模指数算法
	 * 
	 * @param a
	 *            底数
	 * @param b
	 *            指数
	 * @param c
	 *            模
	 * @return 结果
	 */
	public static int quick(int a, int b, int c) {
		int ans = 1; // 记录结果
		a = a % c; // 预处理，使得a处于c的数据范围之下
		while (b != 0) {
			if ((b & 1) != 0)
				ans = (ans * a) % c;
			b >>= 1; // 二进制的移位操作，相当于每次除以2
			a = (a * a) % c; // 不断的加倍
		}
		return ans;
	}

	// 得到最大公约数
	public static int getGcd(int i, int j) {
		if (i < j) {
			int temp = i;
			i = j;
			j = temp;
		}

		int k;
		while ((k = i % j) != 0) {
			i = j;
			j = k;
		}
		return j;
	}
}
