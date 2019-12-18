/*
 * Copyright (c) 2018-2019.‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package cn.smallbun.scaffold.framework.common.toolkit;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static sun.net.util.IPAddressUtil.textToNumericFormatV4;

/**
 * IpUtil
 * @author SanLi [隔壁object港哥][https://www.leshalv.net]
 * Created by 2689170096@qq.com on  2019-01-10 12:50
 */
public class IpUtil {

	private static final String UNKNOWN = "unknown";

	public static boolean internalIp(String ip) {
		byte[] addr = textToNumericFormatV4(ip);
		return internalIp(addr) || "127.0.0.1".equals(ip);
	}

	/**
	 * 是否是内部IP
	 * @param addr
	 * @return
	 */
	private static boolean internalIp(byte[] addr) {
		final byte b0 = addr[0];
		final byte b1 = addr[1];
		// 10.x.x.x/8
		final byte section1 = 0x0A;
		// 172.16.x.x/12
		final byte section2 = (byte) 0xAC;
		final byte section3 = (byte) 0x10;
		final byte section4 = (byte) 0x1F;
		// 192.168.x.x/16
		final byte section5 = (byte) 0xC0;
		final byte section6 = (byte) 0xA8;
		switch (b0) {
			case section1:
				return true;
			case section2:
				if (b1 >= section3 && b1 <= section4) {
					return true;
				}
			case section5:
				return b1 == section6;
			default:
				return false;
		}
	}

	/**
	 * 获取用户真实IP
	 * @param request {@link HttpServletRequest}
	 * @return {@link String} ip地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return UNKNOWN;
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

	/**
	 *获取主机名
	 * @return String
	 */
	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ignored) {
		}
		return "未知";
	}

	public static String getHostIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException ignored) {
		}
		return "127.0.0.1";
	}
}
