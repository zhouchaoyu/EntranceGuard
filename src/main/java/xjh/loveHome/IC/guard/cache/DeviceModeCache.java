package xjh.loveHome.IC.guard.cache;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

import xjh.loveHome.IC.mode.GuardDevice;

/**
 * 设备数据模型缓存
 ***/
public class DeviceModeCache extends CacheName {
	public final static DeviceModeCache CACHE = new DeviceModeCache();

	public DeviceModeCache() {
		setCacheName("deviceMode");
	}

	/***
	 * 添加设备数据模型 其中 采用hastset数据模型 filed 为 GuardDevice no属性值 velue 为 GuardDevice 对象
	 * 
	 * @throws Exception
	 ***/
	public boolean addDeviceMode(GuardDevice device) throws Exception {
		if (device == null) {
			throw new Exception("device  object not null");
		}
		Cache cache = Redis.use();
		if (cache.hset(getCacheName(), device.getNo(), device) == 1) {
			return true;
		} else {
			return false;
		}

	}
	/***
	 * 检查是否存在设备mode
	 * **/
	public boolean exists(String no) {
		Cache cache = Redis.use();
		return cache.hexists(getCacheName(), no);
	}

	public GuardDevice getDeviceMode(String no) {
		Cache cache = Redis.use();
		return cache.hget(getCacheName(), no);
	}
	/**
	 * 获取存储长度
	 * 
	 * ***/
	public long getCacheSize() {
		Cache cache = Redis.use();
		return cache.hlen(getCacheName());
	}
	/***
	 *  获取所有设备list
	 * ***/
	public List<GuardDevice> getDeviceModeList() {
		Map<String, GuardDevice> deviceMap = getDeviceModeMap();
		if (deviceMap != null) {
			List<GuardDevice> list =new ArrayList<GuardDevice>();
			for (Iterator<GuardDevice> iterator = deviceMap.values().iterator(); iterator.hasNext();) {
				GuardDevice device = (GuardDevice) iterator.next();
				list.add(device);
			}
			return list;
		} else {
			return null;
		}
	}
	/****
	 * 获取所有设备map
	 * ***/
	@SuppressWarnings("unchecked")
	public Map<String, GuardDevice> getDeviceModeMap() {
		Cache cache = Redis.use();
		return cache.hgetAll(getCacheName());
	}

}
