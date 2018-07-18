package xjh.loveHome.IC.guard.cache;

/***
 * 缓存加载器。
 * **/
public class CacheLoader   implements InitLoad<Object> {
	public final static CacheLoader   CACHE_LOADER=new CacheLoader(); 
	@Override
	public void initLoad(Object t) {
		DeviceCacheManager.MANAGER.initLoad(DeviceModeCache.CACHE.getDeviceModeList());
	}
	
	
	
	
	
}
