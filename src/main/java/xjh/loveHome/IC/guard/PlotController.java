package xjh.loveHome.IC.guard;

import com.jfinal.core.Controller;

import xjh.loveHome.IC.guard.service.WebGuardResponseCode;
import xjh.loveHome.IC.mode.PropPlot;

public class PlotController  extends Controller {
	public void index() {
		
	}		
	
	
	/***
	 * 小区注册
	 * 
	 * ****/
	public void enroll() {
		
	} 
	
	/***
	 * 小区信息查询
	 * ***/
	
	public void query() {
		Integer pageNo=getParaToInt("pageNo");
		Integer rows=getParaToInt("rows");
		if (pageNo==null||rows==null){
			render(WebGuardResponseCode.RequsetError.toString());
		}
		
		
		
		
	}
	
}
