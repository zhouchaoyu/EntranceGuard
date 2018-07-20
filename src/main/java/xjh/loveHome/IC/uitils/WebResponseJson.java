package xjh.loveHome.IC.uitils;
/***
 * web 响应封装类
 * ***/

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.JsonKit;

public class WebResponseJson {
	private boolean status=false;//1 标识响应成功    0 响应失败
	private int length=0;//数据长度
	public int getLength() {
		return length;
	}


	public boolean isStatus() {
		return status;
	}
	
	private Object[] data;// 响应的内容
	
	private Object page;//响应的page对象
	
	
	public Object getPage() {
		return page;
	}


	public void setPage(Object page) {
		this.page = page;
	}

	private WebGuardResponseCode responseCode;

	public Object getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
		length=data.length;
	}

	public WebGuardResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(WebGuardResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	
	
	public String tojsion() {
		Map<String,Object> jsonMap=new HashMap<>();
		if (responseCode==null) {
			jsonMap.put("status",0);
		}else {
			if (responseCode.getStatus()) {
				jsonMap.put("status",1);
			}else {
				jsonMap.put("status",0);
			}
			jsonMap.put("code",responseCode.getCode());
			jsonMap.put("message",responseCode.getMessage());
		}
		jsonMap.put("page",page);
		jsonMap.put("data",data);
		jsonMap.put("length",getLength());
		return JsonKit.toJson(jsonMap);
		
	}
	
	
	
	
	
	
}
