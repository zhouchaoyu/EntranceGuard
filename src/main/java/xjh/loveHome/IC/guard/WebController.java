package xjh.loveHome.IC.guard;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import xjh.loveHome.IC.uitils.SecurityMonitor;
import xjh.loveHome.IC.uitils.WebGuardResponseCode;

/***
 * 此web控制器负责调度门禁系统的前段网页
 * ***/
@Before(SecurityMonitor.class)
public class WebController extends Controller{
	
	public void index() {
		renderText(WebGuardResponseCode.RequsetError.toString());//请求错误);
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
