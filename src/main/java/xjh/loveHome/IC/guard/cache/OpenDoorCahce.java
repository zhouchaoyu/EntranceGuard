package xjh.loveHome.IC.guard.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/***
 * 开们cache
 * ***/
public class OpenDoorCahce  extends CacheName{
	public static final OpenDoorCahce CAHCE=new OpenDoorCahce();
	public OpenDoorCahce() {
			setCacheName("openDoor");
	}
	
	/**
	 * 设置开门指令
	 * **/
	public boolean setOpenOrder(String no) {
		Cache cache=Redis.use();
		cache.set(getCacheName()+no,"ture");
		if (cache.expire(getCacheName()+no,10)==0) {
			return true;
		}
		return false;
	} 
	
	
	/**
	 * 读取开门指令
	 * 返还true、表示有开门指令
	 * 返还true、表示没有开门指令
	 * 
	 * **/
	public boolean ReadOpenOrder(String no) {
		Cache cache=Redis.use();
		if (cache.exists(getCacheName()+no)) {
			cache.del(getCacheName()+no);
			return true;
		}else {
			return false;
		}
	} 
	
}
