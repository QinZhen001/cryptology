package mima;

import java.util.Scanner;

public class RSA {
	public static void main(String[] arg) {
		int p, q, e, n, fn, d; // ����fnΪ��(n) Ҳ����(p-1)(q-1)
		Scanner sc = new Scanner(System.in);
		System.out.println("������p��ֵ:");
		p = sc.nextInt();
		if (!isPrime(p)) {
			System.out.println("p��ֵ��������");
			System.exit(1);
		}
		System.out.println("������q��ֵ:");
		q = sc.nextInt();
		if (!isPrime(q)) {
			System.out.println("q��ֵ��������");
			System.exit(1);
		}
		System.out.println("������e��ֵ:");
		e = sc.nextInt();
		fn = (p - 1) * (q - 1);
		if (getGcd(fn, e) != 1) {
			System.out.println("e �� ��(n)������");
			System.exit(1);
		}
		n = p * q;
		// ed �� 1 (mod ��(n)) ���ʽ�ӵȼ��� ed - 1 = k��(n)
		int[] temp = extend_gcd(e, fn);
		d = temp[1];
		int x = 0;
		while (true) {
			System.out.println("��ѡ�����");
			System.out.println("1 RSA����");
			System.out.println("2 RSA����");
			System.out.println("3 �˳�");
			x = sc.nextInt();
			if (x == 1) {
				System.out.println("����������:");
				int mingwen = sc.nextInt();
				int C = quick(mingwen, e, n);
				System.out.println("���ܽ��:  " + C);
				System.out.println("\n****************************\n");
			} else if (x == 2) {
				System.out.println("����������:");
				int miwen = sc.nextInt();
				int M = quick(miwen, d, n);
				System.out.println("���ܽ��:  " + M);
				System.out.println("\n****************************\n");
			} else {
				break;
			}
		}
	}

	/**
	 * 
	 * @param n
	 *            ��Ҫ�жϵ���
	 * 
	 * @return true������ false��������
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
	 * ��չ��ŷ������㷨
	 * 
	 * @param a
	 *            �൱��RSA�е�e
	 * @param b
	 *            �൱��RSA�еĦ�(n)
	 * @return result[1] �൱��RSA�е�d
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
	 * ����ȡģָ���㷨
	 * 
	 * @param a
	 *            ����
	 * @param b
	 *            ָ��
	 * @param c
	 *            ģ
	 * @return ���
	 */
	public static int quick(int a, int b, int c) {
		int ans = 1; // ��¼���
		a = a % c; // Ԥ����ʹ��a����c�����ݷ�Χ֮��
		while (b != 0) {
			if ((b & 1) != 0)
				ans = (ans * a) % c;
			b >>= 1; // �����Ƶ���λ�������൱��ÿ�γ���2
			a = (a * a) % c; // ���ϵļӱ�
		}
		return ans;
	}

	// �õ����Լ��
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
