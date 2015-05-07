package com.johnnydemo.gridlayoutmanagerdemo;

import java.util.Random;

/**
 * Created by johnnysung on 15/05/07.
 */
public class Utils {
	public static int[] generateFakeData(int sections) {
		Random rnd = new Random();
		int[] datas = new int[sections];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = rnd.nextInt(10) + 1;
		}
		return datas;
	}

	public static String printArray(int[] data) {
		StringBuilder sb = new StringBuilder();
		sb.append("array size = " + data.length + "  [ ");
		for (int i = 0; i < data.length; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(data[i]);
		}
		sb.append(" ]");
		return sb.toString();
	}
}
