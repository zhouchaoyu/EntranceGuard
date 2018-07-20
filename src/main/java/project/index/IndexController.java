package project.index;

import com.jfinal.core.Controller;

import xjh.loveHome.IC.uitils.WebGuardResponseCode;

public class IndexController extends Controller {
	public void index() {
		renderText(WebGuardResponseCode.RequsetError.toString());
	}
	
	
	public void tolockHRML() {
		render("/WEB-INF/guard/lock.html");
	}
	
	public void toQrCodeHTML() {
			render("/WEB-INF/guard/qrCode.html");
	}
}
	
 