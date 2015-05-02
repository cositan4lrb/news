package com.hust.news.domain;

import java.util.List;

//为Gson生成JavaBean 要求是所有字段必须是public
//要根据HiJson中的key直接复制 避免出错！

public class NewsCenterBean {

	public int retcode;
	
	//data里面啥都有 所以起个名字 下面写类
	public List<NewsCenterData> data;
	//extend就是个空数组 里面有字符串(数字) 定义为List就好了
	public List<String> extend;

	public class NewsCenterData {

		//key为children的字段类型是Array 同样得写成List 之后写类
		//穷尽每一个出现的key
		public List<ChildRen> children;
		public int id;
		public String title;
		public int type;
		public String url;
		public String url1;
		public String dayurl;
		public String excurl;
		public String weekurl;
	}

	public class ChildRen {

		public int id;
		public int type;
		public String title;
		public String url;
	}

}
