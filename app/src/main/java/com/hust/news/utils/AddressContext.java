package com.hust.news.utils;

/**
 * @author jj
 *
 */
public class AddressContext {

	// 不可更改的服务器固定地址 (前缀)
	// 换了服务器 还有东西要改 就是包里的图片的服务器地址 10.0.2.2 就是模拟器地址 也是计算机地址
	public static final String SERVICE_URL = "http://222.20.30.28:8080/zhbj/";
	//新闻中心请求数据的地址
	public static String NEWSCENTER_URL = SERVICE_URL + "categories.json";
}
