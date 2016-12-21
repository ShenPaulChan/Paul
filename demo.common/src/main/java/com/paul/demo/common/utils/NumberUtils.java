/**
 * Copyright reserved by IZHUO.NET
 */
package com.paul.demo.common.utils;

import java.text.DecimalFormat;

/**
 * 数字工具类
 * 
 * @author Paul
 * 
 *         2015年2月12日 下午5:36:10
 */
public class NumberUtils {

	public static final double B = 1000000000.0;
	/** 整数 */
	public static final String F_N = "#";
	/** #.#一位小数 */
	public static final String F_N_P_N = "0.0";
	/** 0.00% 两位小数*/
	public static final String F_N_P_NN_PER = "0.00%";
	/** 123,456.12 */
	public static final String F_N_C_P = ",000.00";

	public static String format(double number, String format) {
		return new DecimalFormat(format).format(number);
	}
	
	public static String toBillion(double num, String format){
		double b = num/B;
		return format(b, format);
	}
	
	public static void main(String[] args) {
		double d = 234354.53434;
		System.out.println(format(d, F_N_P_NN_PER));
	}
	
}
