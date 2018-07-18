package xjh.loveHome.IC.guard.cache;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

import xjh.loveHome.IC.mode.GuardDevice;

/**
 * 设备数据模型缓存,添加设备数据模型 其中 采用hastset数据模型 filed 为 GuardDevice no属性值 velue 为 GuardDevice 对象
 * 为了提高系统性能，采用分布式缓存架构。提供了缓存一致性方法，当从缓存中获取数据时，若没有命中cache，则去数据库取相关的数据。若返回值为空，则直接返还空值，
 * 若返还值存在值，则刷新缓存。当更新数据mode时，会先更新底层DB，更新成功之后同步到缓存。
 * 
 * 
 * 
 ***/
public class DeviceModeCache extends CacheName {
	public final static DeviceModeCache CACHE = new DeviceModeCache();

	public DeviceModeCache() {
		setCacheName("deviceMode");
	}

	/***
	 * 添加设备数据模型 其中 采用hastset数据模型 filed 为 GuardDevice no属性值 velue 为 GuardDevice 对象
	 *	添加一个新的缓存返还true
	 * @throws Exception
	 ***/
	public boolean addDeviceMode(GuardDevice device){
		Cache cache = Redis.use();
		if (cache.hset(getCacheName(), device.getNo(), device) == 1) {
			return true;
		} else {
			return false;
		}
	}
	/***
	 * 检查缓存中是否存在设备mode，若有则直接返还true，没有则返还false
	 * **/
	public boolean exists(String no) {
		Cache cache = Redis.use();
		return cache.hexists(getCacheName(), no);
	}
	/***
	 * 检查缓存中是否存在设备mode，若有则直接返还true，没有则去数据库中获取，并重新判断是否存在mode。
	 * **/
	public boolean existsDB(String no) {
		Cache cache = Redis.use();
		if (!cache.hexists(getCacheName(), no)) {
			if (GuardDevice.dao.isSignined(no)) {
				synchronizeDevice(no);
				return true;
			}else {
				return false;
			}
		}
		return true;
	}
	

	/****
	 * 从缓存中取出mode
	 * 
	 * *****/
	public GuardDevice getDeviceMode(String no) {
		Cache cache = Redis.use();
		return cache.hget(getCacheName(), no);
	}
	
	
	
	/**
	 * 先从缓存中去mode 若不存在，则去数据库取mode,如果数据库存要获取的数据对象，则刷新缓存。
	 * ***/
	public GuardDevice getDeviceModeDB(String no) {
		Cache cache = Redis.use();
		GuardDevice device=cache.hget(getCacheName(), no);
		if (device==null) {
			device=GuardDevice.dao.getDeviceByNo(no);
			if (device!=null) {
				synchronizeDevice(no);
			}
		}
		return device;
	}
	
	
	
	/****
	 * 同步BD数据到缓存
	 * *****/
	public boolean synchronizeDevice(String no) {
		Cache cache = Redis.use();
		cache.hset(getCacheName(),no, GuardDevice.dao.getDeviceByNo(no));
		return true;
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
	
	/****
	 *从数据库刷新缓存
	 * ***/
	public static void RefreshCache() {
		Cache cache = Redis.use();
		cache.del(CACHE.getCacheName());
		List<GuardDevice> devices=GuardDevice.dao.getDeviceALL();
		Map<Object, Object> map=new HashMap<>();
		for (Iterator<GuardDevice> iterator = devices.iterator(); iterator.hasNext();) {
						GuardDevice		guardDevice=iterator.next();
						map.put(guardDevice.getNo(),guardDevice);
		}
		cache.hmset(CACHE.getCacheName(),map);	
	}
	
	
	public void RemoveCache(String no) {
		Cache cache = Redis.use();
		cache.hdel(getCacheName(),no);
	}
	
	
	
}
