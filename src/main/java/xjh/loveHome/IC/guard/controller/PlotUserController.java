package xjh.loveHome.IC.guard.controller;

import com.jfinal.core.Controller;

import xjh.loveHome.IC.uitils.WebGuardResponseCode;

public class PlotUserController  extends Controller{
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
}
