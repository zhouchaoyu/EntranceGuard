package xjh.loveHome.IC.guard.service;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;

import xjh.loveHome.IC.guard.cache.DeviceModeCache;
import xjh.loveHome.IC.guard.cache.DeviceRunNoCache;
import xjh.loveHome.IC.guard.cache.OpenDoorCahce;
import xjh.loveHome.IC.mode.GuardDevice;

/***
 * 设备服务
 * ***/
public class DeviceService {
		
	private static  DeviceService instance;
	
	public enum Factory{
		inst;
		private Factory() {
			if (instance==null) {
				instance=new DeviceService();
			}
		}
		DeviceService  getDeviceService() {
			return instance;
		}
	}
	
	
	public static DeviceService getDeviceService() {
		return Factory.inst.getDeviceService();
	}

	/***
	 *	门锁设备监听开门请求
	 * 
	 * ****/

	public String monitor(String token,HttpServletRequest request) {
		if (!DeviceModeCache.CACHE.exists(token)) {
			GuardDevice device= automateDataload(token,request);
			try {
				if (automate(device)) {
					return String.valueOf(DeviceStateCode.signined.getCode());
				}else {
					return String.valueOf(DeviceResponseCode.Exception.getCode());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			if (DeviceRunNoCache.CACHE.equals(token)) {
				//在设备运行cache
				if (OpenDoorCahce.CAHCE.ReadOpenOrder(token)) {
					return String.valueOf(DeviceResponseCode.OpenDoor.getCode());
				}else {
					return String.valueOf(DeviceResponseCode.CloseDoor.getCode());
				}
			}else {
				//没有在运行列表
				//GuardDevice.dao.f
			}
		}
		return String.valueOf(DeviceResponseCode.Exception.getCode());
	}
	
	
	
	public boolean isSignined(String token) {
		return	GuardDevice.dao.isSignined(token);
	}
	
	/*****
	 * 自动注册
	 * @throws Exception 
	 * *****/
	@Before(Tx.class)
	private boolean automate(GuardDevice guardDevice) throws Exception {
		if (guardDevice.save()) {
			return DeviceModeCache.CACHE.addDeviceMode(guardDevice);
		}
		return false;
	}
	
	private boolean isRunning(String token) {
		return false;
	}
	
	/****
	 * 数据装配
	 * ***/
	private GuardDevice automateDataload(String token,HttpServletRequest request) {
		GuardDevice device=new GuardDevice();
		device.setNo(token);
		device.setIp(request.getRemoteAddr());
		device.setState(DeviceStateCode.signined.getCode());
		device.setType("1101-1102");
		device.setProt(request.getRemotePort());
		return device;
	}
	
	
}
