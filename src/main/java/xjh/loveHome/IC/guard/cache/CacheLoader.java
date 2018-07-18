package xjh.loveHome.IC.guard.cache;

import xjh.loveHome.IC.mode.GuardDevice;

/***
 * 缓存加载器。
 * **/
public class CacheLoader   implements InitLoad<Object> {
	public final static CacheLoader   CACHE_LOADER=new CacheLoader(); 
	@Override
	public void initLoad(Object t) {
		DeviceCacheManager.MANAGER.initLoad(GuardDevice.dao.getDeviceALL());
	}
	
	
	
	
	
}
