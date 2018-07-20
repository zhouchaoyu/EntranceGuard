package xjh.loveHome.IC.guard.route;

import com.jfinal.config.Routes;

import project.index.IndexController;
import xjh.loveHome.IC.guard.WebController;
import xjh.loveHome.IC.guard.controller.DeviceController;
import xjh.loveHome.IC.guard.controller.GuardController;
import xjh.loveHome.IC.guard.controller.PlotController;
import xjh.loveHome.IC.guard.controller.PlotDeviceController;
import xjh.loveHome.IC.guard.controller.PlotUserController;
import xjh.loveHome.IC.guard.controller.QrCodeController;

public class GuardRoute extends Routes {

	@Override
	public void config() {
		add("/", IndexController.class);
		add("/guard", GuardController.class);
		add("/qrCode",QrCodeController.class);
		add("/door",WebController.class,"/WEB-INF/guard/");
		add("/plot",PlotController.class);
		add("/device",DeviceController.class);
		add("/plotDevice",PlotDeviceController.class);
		add("/user",PlotUserController.class);
	}

}
