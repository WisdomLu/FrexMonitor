package com.ocean.handler;

import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Inveno ID算法
 * 
 * 用于生成自描述的ID
 * 
 * @author 余学好(qq:398520134)
 * @date 2015年12月12日
 */
public class IdGenerator {

	private static final String AD_PREFIX = "0001";

	private static final int BASE_YEAR = 2015;// 基础年份

	private static final String SOFT_AD_STR = "0001000000000010100000000000000000000000000000000000000000000000";// 软文
	private static final String DSP_STR = "0001000000000000100000000000000000000000000000000000000000000000";

	private static final String AD_STR = "0001000000000000000000000000000000000000000000000000000000000000";

	private static final String PMS_STR = "0001000000000000000000000000000000000000000000000000000000000000";
	private static final String UID_STR = "0011000000000000000000000000000000000000000000000000000000000000";

	private static AtomicInteger SOFT_COUNTER = new AtomicInteger();
	private static AtomicInteger DSP_COUNTER = new AtomicInteger();
	private static AtomicInteger PMS_COUNTER = new AtomicInteger();
	private static AtomicInteger UID_COUNTER = new AtomicInteger();
	private static AtomicInteger AD_COUNTER = new AtomicInteger();

	private static byte SOFT_LAST_SECOND = -1;
	private static byte DSP_LAST_SECOND = -1;
	private static byte PMS_LAST_SECOND = -1;
	private static byte UID_LAST_SECOND = -1;
	private static byte AD_LAST_SECOND = -1;

	private static final Object LOCK_SOFT = new Object();
	private static final Object LOCK_DSP = new Object();
	private static final Object LOCK_PMS = new Object();
	private static final Object LOCK_UID = new Object();

	private static final Object LOCK_AD = new Object();

	private static final String AD_SRC_SOFT_VALUE = "00101";// 软文(DSP)
	private static final String AD_SRC_DSP_VALUE = "00001";// DSP 广告
	private static final String AD_SRC_PMS_VALUE = "00000";// PMS 广告

	public static final String AD_SRC_DSP = "DSP";
	public static final String AD_SRC_PMS = "PMS";
	public static final String AD_SRC_SOFT = "SOFT";
	public static final String BIT_0 = "0";

	public static long genThirdDspId(int nodeId) {
        //node index range 1-255
		Calendar c = Calendar.getInstance(Locale.CHINA);
		int year = c.get(Calendar.YEAR) - BASE_YEAR;
		int month = c.get(Calendar.MONTH);// 从0开始
		int day = c.get(Calendar.DAY_OF_MONTH);// 从1开始

		int hour = c.get(Calendar.HOUR_OF_DAY);// 24hour
		int min = c.get(Calendar.MINUTE);
		byte second = (byte) c.get(Calendar.SECOND);

		char[] rs = AD_STR.toCharArray();

		replace(rs, 16, nodeId);

		// process year
		replace(rs, 24, year);

		// process month
		replace(rs, 28, month);

		// process day
		replace(rs, 33, day);

		// process hour
		replace(rs, 38, hour);

		// process min
		replace(rs, 44, min);

		// process second
		replace(rs, 50, second);

		int index = 0;
		if (AD_LAST_SECOND == second) {
			index = AD_COUNTER.incrementAndGet();
		} else {
			synchronized (LOCK_AD) {
				if (AD_LAST_SECOND == second) {
					index = DSP_COUNTER.incrementAndGet();
				} else {
					AD_LAST_SECOND = second;
					AD_COUNTER.set(0);
				}
			}
		}
		// process index
		replace(rs, 63, index);
		return Long.parseLong(new String(rs), 2);

	}

	public static long getUId(byte nodeId) {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		int year = c.get(Calendar.YEAR) - BASE_YEAR;
		int month = c.get(Calendar.MONTH);// 从0开始
		int day = c.get(Calendar.DAY_OF_MONTH);// 从1开始

		int hour = c.get(Calendar.HOUR_OF_DAY);// 24hour
		int min = c.get(Calendar.MINUTE);
		byte second = (byte) c.get(Calendar.SECOND);

		char[] rs = UID_STR.toCharArray();

		replace(rs, 11, nodeId);

		// process year
		replace(rs, 24, year);

		// process month
		replace(rs, 28, month);

		// process day
		replace(rs, 33, day);

		// process hour
		replace(rs, 38, hour);

		// process min
		replace(rs, 44, min);

		// process second
		replace(rs, 50, second);

		int index = 0;
		if (UID_LAST_SECOND == second) {
			index = UID_COUNTER.incrementAndGet();
		} else {
			synchronized (LOCK_UID) {
				if (UID_LAST_SECOND == second) {
					index = UID_COUNTER.incrementAndGet();
				} else {
					UID_LAST_SECOND = second;
					UID_COUNTER.set(0);
				}
			}
		}
		// process index
		replace(rs, 63, index);
		return Long.parseLong(new String(rs), 2);
	}

	public static String getADSrc(long id) {
		String bits = Long.toBinaryString(id);
		while (bits.length() < Long.SIZE) {
			bits = BIT_0 + bits;
		}
		bits = bits.substring(12, 17);
		if (AD_SRC_PMS_VALUE.equals(bits)) {
			return AD_SRC_PMS;
		} else if (AD_SRC_DSP_VALUE.equals(bits)) {
			return AD_SRC_DSP;
		} else if (AD_SRC_SOFT_VALUE.equals(bits)) {
			return AD_SRC_SOFT;
		}
		return String.valueOf(Integer.parseInt(bits, 2));
	}

	/**
	 * 是否是DSP广告
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isDSPAD(long id) {
		String src = getADSrc(id);
		return AD_SRC_DSP.equals(src) || AD_SRC_SOFT.equals(src);
	}

	/**
	 * 是否是软文
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isSostAD(long id) {
		return AD_SRC_SOFT.equals(getADSrc(id));
	}

	/**
	 * 是否是PMS广告
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isPMSAD(long id) {
		return AD_SRC_PMS.equals(getADSrc(id));
	}

	/**
	 * 测试是否是广告ID
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isAdId(long id) {
		String bits = Long.toBinaryString(id);
		while (bits.length() < Long.SIZE) {
			bits = BIT_0 + bits;
		}
		return bits.startsWith(AD_PREFIX);
	}

	/**
	 * 为DSP系统生成广告ID
	 * 
	 * @return
	 */
	public static long genADId4DSP() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		int year = c.get(Calendar.YEAR) - BASE_YEAR;
		int month = c.get(Calendar.MONTH);// 从0开始
		int day = c.get(Calendar.DAY_OF_MONTH);// 从1开始

		int hour = c.get(Calendar.HOUR_OF_DAY);// 24hour
		int min = c.get(Calendar.MINUTE);
		byte second = (byte) c.get(Calendar.SECOND);

		char[] rs = DSP_STR.toCharArray();

		// process year
		replace(rs, 24, year);

		// process month
		replace(rs, 28, month);

		// process day
		replace(rs, 33, day);

		// process hour
		replace(rs, 38, hour);

		// process min
		replace(rs, 44, min);

		// process second
		replace(rs, 50, second);

		int index = 0;
		if (DSP_LAST_SECOND == second) {
			index = DSP_COUNTER.incrementAndGet();
		} else {
			synchronized (LOCK_DSP) {
				if (DSP_LAST_SECOND == second) {
					index = DSP_COUNTER.incrementAndGet();
				} else {
					DSP_LAST_SECOND = second;
					DSP_COUNTER.set(0);
				}
			}
		}
		// process index
		replace(rs, 63, index);
		return Long.parseLong(new String(rs), 2);

	}

	/**
	 * 生成广告软文广告ID
	 * 
	 * @return
	 */
	public static long genADId4SoftAD() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		int year = c.get(Calendar.YEAR) - BASE_YEAR;
		int month = c.get(Calendar.MONTH);// 从0开始
		int day = c.get(Calendar.DAY_OF_MONTH);// 从1开始

		int hour = c.get(Calendar.HOUR_OF_DAY);// 24hour
		int min = c.get(Calendar.MINUTE);
		byte second = (byte) c.get(Calendar.SECOND);

		char[] rs = SOFT_AD_STR.toCharArray();

		// process year
		replace(rs, 24, year);

		// process month
		replace(rs, 28, month);

		// process day
		replace(rs, 33, day);

		// process hour
		replace(rs, 38, hour);

		// process min
		replace(rs, 44, min);

		// process second
		replace(rs, 50, second);

		int index = 0;
		if (SOFT_LAST_SECOND == second) {
			index = SOFT_COUNTER.incrementAndGet();
		} else {
			synchronized (LOCK_SOFT) {
				if (SOFT_LAST_SECOND == second) {
					index = SOFT_COUNTER.incrementAndGet();
				} else {
					SOFT_LAST_SECOND = second;
					SOFT_COUNTER.set(0);
				}
			}
		}
		// process index
		replace(rs, 63, index);
		return Long.parseLong(new String(rs), 2);

	}

	/**
	 * 由于PMS系统 本身用的是int作为ID，而且引用比较多，修改成本高，因此，在PMSAdapter中，使用数据转换器
	 * 
	 * @param marketId
	 * @return
	 */
	public static long genADId4PMS(int marketId) {
		String binary = Integer.toBinaryString(marketId);
		String tmp = PMS_STR.substring(0, PMS_STR.length() - binary.length())
				+ binary;
		return Long.parseLong(tmp, 2);
	}

	// 找到原始的PMSID，将包装的ID还原
	public static int getOriginalPMSId(long id) {
		String tmp = Long.toBinaryString(id);
		tmp = tmp.substring(1);
		return Integer.parseInt(tmp, 2);
	}

	/**
	 * 为PMS系统生成广告ID
	 * 
	 * @return
	 */
	@Deprecated
	public static long genADId4PMS() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		int year = c.get(Calendar.YEAR) - BASE_YEAR;
		int month = c.get(Calendar.MONTH);// 从0开始
		int day = c.get(Calendar.DAY_OF_MONTH);// 从1开始

		int hour = c.get(Calendar.HOUR_OF_DAY);// 24hour
		int min = c.get(Calendar.MINUTE);
		byte second = (byte) c.get(Calendar.SECOND);

		char[] rs = PMS_STR.toCharArray();

		// process year
		replace(rs, 24, year);

		// process month
		replace(rs, 28, month);

		// process day
		replace(rs, 33, day);

		// process hour
		replace(rs, 38, hour);

		// process min
		replace(rs, 44, min);

		// process second
		replace(rs, 50, second);

		int index = 0;
		if (PMS_LAST_SECOND == second) {
			index = PMS_COUNTER.incrementAndGet();
		} else {
			synchronized (LOCK_PMS) {
				if (PMS_LAST_SECOND == second) {
					index = PMS_COUNTER.incrementAndGet();
				} else {
					PMS_LAST_SECOND = second;
					PMS_COUNTER.set(0);
				}
			}
		}
		// process index
		replace(rs, 63, index);
		return Long.parseLong(new String(rs), 2);

	}

	private static void replace(char[] rs, int indexEnd, int str) {
		String bits = Integer.toBinaryString(str);
		int len = bits.length();
		for (int i = len - 1; i >= 0; i--) {
			rs[indexEnd--] = bits.charAt(i);
		}
	}


}
