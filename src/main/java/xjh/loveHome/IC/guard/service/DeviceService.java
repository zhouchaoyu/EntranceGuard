package xjh.loveHome.IC.guard.service;

import javax.servlet.http.HttpServletRequest;

import xjh.loveHome.IC.guard.cache.DeviceModeCache;
import xjh.loveHome.IC.guard.cache.DeviceRunCache;
import xjh.loveHome.IC.guard.cache.OpenDoorCahce;
import xjh.loveHome.IC.mode.GuardDevice;

/***
 * 设备服务
 ***/
public class DeviceService {

	private static DeviceService instance;

	public enum Factory {
		inst;
		private Factory() {
			if (instance == null) {
				instance = new DeviceService();
			}
		}

		DeviceService getDeviceService() {
			return instance;
		}
	}

	public static DeviceService getDeviceService() {
		return Factory.inst.getDeviceService();
	}

	/***
	 * 门锁设备监听开门请求
	 * 
	 ****/

	public String monitor(String token, HttpServletRequest request) {
		if (!DeviceModeCache.CACHE.existsDB(token)) {
			//1.判断系统是否注册了该设备
			//2. 没有注册将自动注册
			GuardDevice device = guardDeviceDataload(token, request);//读取request域封装GuardDevice 对象
			if (device.save()) {
				return DeviceStateCode.signined.toString();
			} else {
				return DeviceResponseCode.RequsetError.toString();
			}

		} else {
			//设备已注册到系统
			//1.判断设备是否在可运行状态表 
			if (DeviceRunCache.CACHE.existsAndread(token)) {
				// 在设备运行cache
				if (OpenDoorCahce.CAHCE.ReadOpenOrder(token)) {
					return DeviceResponseCode.OpenDoor.toString();
				} else {
					return DeviceResponseCode.CloseDoor.toString();
				}
			} else {
				// 没有在运行列表
				// 1.判断设备是否在可运行列表
				try {
					return GuardDevice.dao.getState(token).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return DeviceResponseCode.Exception.toString();
	}


	/****
	 * 数据装配
	 ***/
	private GuardDevice guardDeviceDataload(String token, HttpServletRequest request) {
		GuardDevice device = new GuardDevice();
		device.setNo(token);
		device.setIp(request.getRemoteAddr());
		device.setState(DeviceStateCode.signined.getCode());
		device.setType("1101-1102");
		device.setProt(request.getRemotePort());
		device.setCommunication(3);
		return device;
	}

}
