package xjh.loveHome.IC.uitils;

import java.util.List;

import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class PageMode {
	private List<?> data;//pageMode封装的mode数据对象集
	private int pageNo;//页号
	private int dataRow;//行数
	private int totalpage;//总页数
	private int pageRow;//当页数据数
	private PageMode() {
	}
	/***
	 * 将该对象解析成json数据
	 * ****/	
	public String parseJson() {
		return JsonKit.toJson(this);
	}
	
	
	
	public PageMode(Page<?> page) {
		data=page.getList();
		pageNo=page.getPageNumber();
		dataRow=page.getTotalRow();
		totalpage=page.getTotalPage();
		pageRow=page.getPageSize();
	}
	
	
	
}
