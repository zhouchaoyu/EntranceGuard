package xjh.loveHome.IC.guard.controller;

import java.io.IOException;
import java.io.OutputStream;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import xjh.loveHome.IC.guard.DeviceResponseCode;
import xjh.loveHome.IC.guard.RemoteDeviceInfoIntercept;
import xjh.loveHome.IC.uitils.QrCodeCreateUtil;

public class QrCodeController extends Controller {
	public void index() {
		renderText(DeviceResponseCode.RequsetError.toString());
	}

	@Before(RemoteDeviceInfoIntercept.class)
	public void takeQR() throws IOException {
		String message = null;
		OutputStream outputStream = null;
		try {
			String token = getPara("token");
			if (token == null || token.equals("")) {
				message = DeviceResponseCode.ParaError.toString();
			} else {
				// 获取响应流并开始创建二维码
				outputStream = getResponse().getOutputStream();
				QrCodeCreateUtil.createQrCode(outputStream,"http://192.168.0.118:8080/EntranceGuard//guard/toHTML?token="+token, 50, "JPEG");
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
