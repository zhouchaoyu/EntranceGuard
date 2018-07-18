package xjh.loveHome.IC.guard.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
/***
 *	可运行设备编号缓存* 
 * ****/
public class DevicEnableNOCache extends CacheName {
	public DevicEnableNOCache() {
		setCacheName("devicEnableNO");
	}
	
	public boolean addDevice(String deviceNo) {
		Cache cache=Redis.use();
		if (cache.sadd(getCacheName(),new Object[] {deviceNo})==0) {
			return true;
		}else {
			return false;
		}
	}
}
