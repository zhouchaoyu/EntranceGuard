package xjh.loveHome.IC.guard.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
/****
 * 正在运行的设备缓存
 * 
 * ***/
public class DeviceRunNoCache extends CacheName {
	
	public static final DeviceRunNoCache CACHE=new DeviceRunNoCache();
	public void DeviceNOCache() {
		setCacheName("deviceRunNO");
	}
	
	public String addDevice(String deviceNo) {
		Cache cache=Redis.use();
		return cache.set(getCacheName(), deviceNo);
	}
	
	
	public boolean exists(String deviceNo) {
		Cache cache=Redis.use();
		return cache.hexists(getCacheName(), deviceNo);
	}
	
	
}
