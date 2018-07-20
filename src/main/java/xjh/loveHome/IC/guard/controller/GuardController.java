package xjh.loveHome.IC.guard.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import xjh.loveHome.IC.guard.DeviceResponseCode;
import xjh.loveHome.IC.guard.RemoteDeviceInfoIntercept;
import xjh.loveHome.IC.guard.service.DeviceService;
/***
 *门卫控制器： 此控制器door负责门的开放与闭合
 *@author xiaoyu
 *@version 1.0
 *@since 2018.07.10
 *
 * ***/
public class GuardController extends Controller {
	public void index() {
		renderText(DeviceResponseCode.RequsetError.toString());
	}
	@Before(RemoteDeviceInfoIntercept.class)
	public void door() {
		String message = null;
		try {
			String token = getPara("token");
			if (token == null || token.equals("")) {
				message=DeviceResponseCode.ParaError.toString();
			} else {//监听开门
				DeviceService deviceService=DeviceService.getDeviceService();
				message=deviceService.monitor(token,getRequest());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (message == null) {
				renderText(DeviceResponseCode.RequsetError.toString());
			} else {
				renderText(message);
			}

		}

	}
	
	
	
	
	
	
	
}
