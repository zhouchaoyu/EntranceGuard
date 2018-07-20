package xjh.loveHome.IC.guard.controller;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

import xjh.loveHome.IC.guard.PageDataQueryInterceptor;
import xjh.loveHome.IC.mode.PropPlot;
import xjh.loveHome.IC.uitils.PageMode;
import xjh.loveHome.IC.uitils.PageModeName;
import xjh.loveHome.IC.uitils.SecurityMonitor;
import xjh.loveHome.IC.uitils.StringUitils;
import xjh.loveHome.IC.uitils.WebGuardResponseCode;
import xjh.loveHome.IC.uitils.WebResponseJson;
@Before(SecurityMonitor.class)
public class PlotController  extends Controller {
	public void index() {
	}		
	
	
	/***
	 * 小区注册
	 * 
	 * ****/
	public void enroll() {
		PropPlot plot=getModel(PropPlot.class,null,true);
		WebResponseJson responseJson=new WebResponseJson();
		if (plot.save()) {
			responseJson.setResponseCode(WebGuardResponseCode.RequsetSuccess);
		}else {
			responseJson.setResponseCode(WebGuardResponseCode.RequsetFail);
		}
		renderJson(responseJson.tojsion());
	} 
	
	/***
	 * 小区信息查询列表
	 * 		根据城市名称查询小区
	 * 
	 * 
	 * ***/
	@Before(PageDataQueryInterceptor.class)
	public void queryProps() {
		Map<String,String>   queryparam=new HashMap<>();
		String city=getPara("city","");
		if (StringUitils.isBlank(city)) {
			WebResponseJson responseJson=new WebResponseJson();
			responseJson.setResponseCode(WebGuardResponseCode.RequsetError);
			renderJson(responseJson.tojsion());
			return;
		}
		queryparam.put("city",getPara("city",""));
		Page<PropPlot> page=PropPlot.dao.queryProps(getParaToInt(PageModeName.pageNo.getName()),getParaToInt(PageModeName.pageRow.getName()),queryparam);
		WebResponseJson responseJson=new WebResponseJson();
		if (page.getTotalRow()>0) {
			responseJson.setResponseCode(WebGuardResponseCode.RequsetSuccess);
			PageMode pageMode=new PageMode(page);
			responseJson.setPage(pageMode);
		}
		renderJson(responseJson.tojsion());
	}
	
	
	
	
	

	/***
	 * 小区信息查询列表
	 * 		根据城市名称查询，小区名称查询精确查询小区
	 * 
	 * 
	 * ***/
	public void queryProp() {
		Map<String,String>   queryparam=new HashMap<>();
		String city=getPara("city","");
		String name=getPara("name","");
		if (StringUitils.isBlank(city)||StringUitils.isBlank(name)) {
			WebResponseJson responseJson=new WebResponseJson();
			responseJson.setResponseCode(WebGuardResponseCode.RequsetError);
			renderJson(responseJson.tojsion());
			return;
		}
		queryparam.put("city",city);
		queryparam.put("name",name);
		Page<PropPlot> page=PropPlot.dao.queryProps(1,Integer.MAX_VALUE,queryparam);
		WebResponseJson responseJson=new WebResponseJson();
		if (page.getTotalRow()==1) {
			responseJson.setResponseCode(WebGuardResponseCode.RequsetSuccess);
			responseJson.setData(page.getList().toArray());
		}
		renderJson(responseJson.tojsion());
	}
	
}
