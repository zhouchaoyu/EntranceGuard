package xjh.loveHome.IC.guard.route;

import com.jfinal.config.Routes;

import project.index.IndexController;
import xjh.loveHome.IC.guard.DeviceController;
import xjh.loveHome.IC.guard.GuardController;
import xjh.loveHome.IC.guard.PlotController;
import xjh.loveHome.IC.guard.QrCodeController;
import xjh.loveHome.IC.guard.WebController;

public class GuardRoute extends Routes {

	@Override
	public void config() {
		add("/", IndexController.class);
		add("/guard", GuardController.class);
		add("/qrCode",QrCodeController.class);
		add("/door",WebController.class,"/WEB-INF/guard/");
		add("/plot",PlotController.class);
		add("/device",DeviceController.class);
	}

}
