package xjh.loveHome.IC.guard.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.tx.Tx;

import xjh.loveHome.IC.guard.cache.DeviceModeCache;
import xjh.loveHome.IC.guard.cache.DeviceRunNoCache;
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
			// 没有注册将自动注册
			GuardDevice device = automateDataload(token, request);
			if (automate(device)) {
				return DeviceStateCode.signined.toString();
			} else {
				return DeviceResponseCode.RequsetError.toString();
			}

		} else {
			if (DeviceRunNoCache.CACHE.existsAndread(token)) {
				// 在设备运行cache
				if (OpenDoorCahce.CAHCE.ReadOpenOrder(token)) {
					return DeviceResponseCode.OpenDoor.toString();
				} else {
					return DeviceResponseCode.CloseDoor.toString();
				}
			} else {
				// 没有在运行列表
				try {
					return GuardDevice.dao.getState(token).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return DeviceResponseCode.Exception.toString();
	}

	public boolean isSignined(String token) {
		return GuardDevice.dao.isSignined(token);
	}

	/*****
	 * 自动注册
	 * 
	 * @throws Exception
	 *****/

	private boolean automate(GuardDevice guardDevice) {
		return Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				if (guardDevice.save()) {
					return DeviceModeCache.CACHE.addDeviceMode(guardDevice);
				}
				return false;
			}
		});

	}

	/****
	 * 数据装配
	 ***/
	private GuardDevice automateDataload(String token, HttpServletRequest request) {
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
