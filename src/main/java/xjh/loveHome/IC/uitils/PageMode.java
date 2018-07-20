package xjh.loveHome.IC.uitils;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;

public class PageMode {
	private List<?> pagedata;//pageMode封装的mode数据对象集
	private int pageNo;//页号
	private int dataRow;//数据总条数
	private int totalpage;//总页数
	private int pageRow;//当页数据数

	private PageMode() {
	}
	
	

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getDataRow() {
		return dataRow;
	}
	public void setDataRow(int dataRow) {
		this.dataRow = dataRow;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getPageRow() {
		return pageRow;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public PageMode(Page<?> page) {
		setPagedata(page.getList());
		pageNo=page.getPageNumber();
		dataRow=page.getTotalRow();
		totalpage=page.getTotalPage();
		pageRow=page.getPageSize();
	}



	public List<?> getPagedata() {
		return pagedata;
	}



	public void setPagedata(List<?> pagedata) {
		this.pagedata = pagedata;
	}
	
	 
	
	
}
