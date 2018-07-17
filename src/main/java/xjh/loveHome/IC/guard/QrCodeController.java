package xjh.loveHome.IC.guard;

import java.io.IOException;
import java.io.OutputStream;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import xjh.loveHome.IC.uitils.QrCodeCreateUtil;

public class QrCodeController extends Controller {
	public void index() {
		renderText("requset error");
	}

	@Before(RemoteDeviceInfoIntercept.class)
	public void takeQR() throws IOException {
		String message = null;
		OutputStream outputStream = null;
		try {
			String token = getPara("token");
			if (token == null || token.equals("")) {
				message = "requset parameter error";
			} else {
				// 获取响应流并开始创建二维码
				outputStream = getResponse().getOutputStream();
				QrCodeCreateUtil.createQrCode(outputStream,"http://192.168.0.133:8080/EntranceGuard/qrCode/takeQR?token=112", 50, "JPEG");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream == null) {
				renderText(message);
			} else {
				outputStream.flush();
				renderNull();

			}

		}

	}

}
