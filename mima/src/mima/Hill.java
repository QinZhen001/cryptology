package mima;

import java.util.Scanner;

public class Hill {

	static int[][] key = { { 17, 17, 5 }, { 21, 18, 21 }, { 2, 2, 19 }, };

	static int[] temp = { 1, 0, 0, };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Hill hill = new Hill();
		System.out.println("请输入需要Hill加密的明文:");
		String plainttext = input.nextLine();

		String ciphertext = hill.encode(plainttext.toUpperCase());
		System.out.println(ciphertext);

		System.out.println();
		System.out.println("Hill解密:");
		String plainttext2 = hill.decode(ciphertext);

		System.out.println("解密后的明文为:");
		System.out.println(plainttext2.toLowerCase());

		input.close();
	}

	private String decode(String ciphertext) {
		int[][] key2 = new int[key.length][key[0].length];
		key2 = getReverseMartrix(key);
		System.out.println("加密密钥逆矩阵为:");
		showMartrix(key2);
		return getDecrypttext(key2, ciphertext);
	}

	private String getDecrypttext(int[][] key2, String ciphertext) {
		// TODO Auto-generated method stub
		int temp1, temp2, temp3;
		StringBuilder plainttext = new StringBuilder();
		for (int i = 0; i < ciphertext.length(); i += 3) {
			temp1 = key2[0][0] * (ciphertext.charAt(i) - 'A') + key2[0][1]
					* (ciphertext.charAt(i + 1) - 'A') + key2[0][2]
					* (ciphertext.charAt(i + 2) - 'A');
			temp2 = key2[1][0] * (ciphertext.charAt(i) - 'A') + key2[1][1]
					* (ciphertext.charAt(i + 1) - 'A') + key2[1][2]
					* (ciphertext.charAt(i + 2) - 'A');
			temp3 = key2[2][0] * (ciphertext.charAt(i) - 'A') + key2[2][1]
					* (ciphertext.charAt(i + 1) - 'A') + key2[2][2]
					* (ciphertext.charAt(i + 2) - 'A');
			plainttext.append((char) ('A' + temp1 % 26));
			plainttext.append((char) ('A' + temp2 % 26));
			plainttext.append((char) ('A' + temp3 % 26));
		}

		return plainttext.toString();
	}

	private void showMartrix(int[][] key2) {
		for (int i = 0; i < key2.length; i++) {
			for (int j = 0; j < key2[0].length; j++) {
				System.out.print(key2[i][j] + " ");
			}
			System.out.println();
		}
	}

	private int[][] getReverseMartrix(int[][] key2) {

		int[][] key = new int[key2.length][key2[0].length];
		for (int num = 0; num < 3; num++)
			for (int i = 0; i < 26; i++)
				for (int j = 0; j < 26; j++)
					for (int k = 0; k < 26; k++) {
						if ((i * 17 + j * 21 + k * 2) % 26 == temp[num % 3]
								&& ((i * 17 + j * 18 + k * 2) % 26 == temp[(num + 2) % 3])
								&& ((i * 5 + j * 21 + k * 19) % 26 == temp[(num + 1) % 3])) {
							key[num][0] = i;
							key[num][1] = j;
							key[num][2] = k;
						}
					}
		return key;
	}

	private String encode(String plainttext) {
		int temp1 = 0, temp2, temp3;
		StringBuilder cipertext = new StringBuilder();
		for (int i = 0; i < plainttext.length(); i += 3) {
			temp1 = key[0][0] * (plainttext.charAt(i) - 'A') + key[0][1]
					* (plainttext.charAt(i + 1) - 'A') + key[0][2]
					* (plainttext.charAt(i + 2) - 'A');
			temp2 = key[1][0] * (plainttext.charAt(i) - 'A') + key[1][1]
					* (plainttext.charAt(i + 1) - 'A') + key[1][2]
					* (plainttext.charAt(i + 2) - 'A');
			temp3 = key[2][0] * (plainttext.charAt(i) - 'A') + key[2][1]
					* (plainttext.charAt(i + 1) - 'A') + key[2][2]
					* (plainttext.charAt(i + 2) - 'A');
			cipertext.append((char) ('A' + temp1 % 26));
			cipertext.append((char) ('A' + temp2 % 26));
			cipertext.append((char) ('A' + temp3 % 26));
		}

		return cipertext.toString();
	}

}
