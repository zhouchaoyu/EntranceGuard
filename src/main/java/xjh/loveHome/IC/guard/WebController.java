package xjh.loveHome.IC.guard;

import com.jfinal.core.Controller;

import xjh.loveHome.IC.guard.service.WebGuardResponseCode;

/***
 * 此web控制器负责调度门禁系统的前段网页
 * ***/
public class WebController extends Controller{
	
	public void index() {
		renderText(WebGuardResponseCode.RequsetError.toString());//请求错误);
	}
	
	/**
	 *用户 请求开门
	 * 
	 * ***/
	public void openDoor() {
		String token = getPara("token");
		if (token == null || token.equals("")) {
			renderText(WebGuardResponseCode.ServerBusy.toString());
		}
	}
	/**
	 * 调度到开门页面
	 * 
	 * **/
	public void toHTML() {
		String token = getPara("token");
		if (token == null || token.equals("")) {
			renderText(WebGuardResponseCode.ServerBusy.toString());
		}
		setAttr("token", token);
		render("openDoor.html");
	}
}
