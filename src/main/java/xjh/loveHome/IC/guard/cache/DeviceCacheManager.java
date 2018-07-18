package xjh.loveHome.IC.guard.cache;

import java.util.Iterator;
import java.util.List;

import xjh.loveHome.IC.guard.service.DeviceStateCode;
import xjh.loveHome.IC.mode.GuardDevice;
/**
 * 缓存管理器
 * ***/
public class DeviceCacheManager implements InitLoad<List<GuardDevice>> {
		public static final DeviceCacheManager MANAGER=new DeviceCacheManager();
		private final DeviceModeCache modeCache=DeviceModeCache.CACHE;
		private final DeviceRunCache runNoCache=DeviceRunCache.CACHE;
		
		public void putCache(GuardDevice device) {
			modeCache.addDeviceMode(device);
			if (device.getState()==DeviceStateCode.started.getCode()) {
				runNoCache.addDevice(device.getNo());
			}
		}
		
		/***
		 * 移除缓存
		 * ***/
		public void removeCache(GuardDevice device) {
			modeCache.RemoveCache(device.getNo());
			runNoCache.RemoveCache(device.getNo());
		}
		
		/***
		 * 刷新数据缓存
		 * ****/
		public void refresh(GuardDevice device) {
			modeCache.addDeviceMode(device);
			runNoCache.addDevice(device.getNo());
		}

	
		@Override
		public void initLoad(List<GuardDevice> devices) {
			for (Iterator<GuardDevice> iterator = devices.iterator(); iterator.hasNext();) {
				GuardDevice guardDevice = (GuardDevice) iterator.next();
				putCache(guardDevice);
			}
		}
		
		
		
}
