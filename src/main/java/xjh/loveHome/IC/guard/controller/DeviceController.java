package xjh.loveHome.IC.guard.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import xjh.loveHome.IC.guard.PageDataQueryInterceptor;
import xjh.loveHome.IC.mode.GuardDevice;
import xjh.loveHome.IC.uitils.PageMode;
import xjh.loveHome.IC.uitils.PageModeName;
import xjh.loveHome.IC.uitils.SecurityMonitor;
import xjh.loveHome.IC.uitils.StringUitils;
import xjh.loveHome.IC.uitils.WebGuardResponseCode;
import xjh.loveHome.IC.uitils.WebResponseJson;


/********
 * 设备控制器
 * 
 * 
 * *********/
@Before(SecurityMonitor.class)
public class DeviceController extends Controller {
	/*****
	 * 设备查询
	 * *****/
	@Before(PageDataQueryInterceptor.class)
	public void queryDevices() {
		Map<String,String>   queryparam=new HashMap<>();
		String state=getPara("state","");
		if (StringUitils.isBlank(state)) {
			WebResponseJson responseJson=new WebResponseJson();
			responseJson.setResponseCode(WebGuardResponseCode.RequsetError);
			renderJson(responseJson.tojsion());
			return;
		}
		queryparam.put("state",getPara("state",""));
		Page<GuardDevice> page=GuardDevice.dao.queryDevices(getParaToInt(PageModeName.pageNo.getName()),getParaToInt(PageModeName.pageRow.getName()),queryparam);
		WebResponseJson responseJson=new WebResponseJson();
		if (page.getPageSize()>0) {
			responseJson.setResponseCode(WebGuardResponseCode.RequsetSuccess);
			PageMode pageMode=new PageMode(page);
			responseJson.setPage(pageMode);
		}
		renderJson(responseJson.tojsion());
	}
	
	
	
	
	
	
}
