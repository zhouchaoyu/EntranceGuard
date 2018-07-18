package xjh.loveHome.IC.guard.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
/****
 * 正在运行的设备缓存,采用hastset数据模型 filed 为 GuardDevice no属性值 velue 为  time 访问周期间隔参数
 * ***/
public class DeviceRunNoCache extends CacheName {
	
	public static final DeviceRunNoCache CACHE=new DeviceRunNoCache();
	public void DeviceNOCache() {
		setCacheName("deviceRunNO");
	}
	/***
	 * 添加运行设备cache
	 * **/
	public boolean addDevice(String deviceNo) {
		Cache cache=Redis.use();
		if (cache.hset(getCacheName(),deviceNo,System.currentTimeMillis())==1) {
			return true;
		}
		return false;
	}
	
	/***
	 * 判断设备cache是否存在
	 * 
	 * ***/
	public boolean exists(String deviceNo) {
		Cache cache=Redis.use();
		return cache.hexists(getCacheName(), deviceNo);
	}
	
	/******
	 * 返回上一次访问到这一次访问的间隔时间（单位）   s秒
	 * 
	 * ****/
	public long objectIdletime(String deviceNo) {
		Cache cache=Redis.use();
		long lasttime=cache.hget(getCacheName(),deviceNo);
		return System.currentTimeMillis()-lasttime;
	}
	
	/***
	 * 判断设备cache是否存在并刷新访问时间周期
	 * 
	 * ***/
	public boolean existsAndread(String deviceNo) {
		Cache cache=Redis.use();
		if (cache.hexists(getCacheName(), deviceNo)) {
			cache.hset(getCacheName(),deviceNo,System.currentTimeMillis());
			return true;
		}
		return false;
	}
	
	
	
}
