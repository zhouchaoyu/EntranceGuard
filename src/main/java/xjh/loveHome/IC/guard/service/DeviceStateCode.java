package xjh.loveHome.IC.guard.service;

/***
 *@author xiaoyu
 *@version 1.0 
 *@since 2018.07.13 
 * 设备状态码枚举
 * ***/
public enum DeviceStateCode {
	/**已注册***/
	signined,
	/***已启用****/
	started,
	/***故障****/
	fault,
	/***已废弃****/
	abandoned,
	/***已停用****/
	stoped,
	/***已关联****/
	related;
	
	public  int getCode(){
		if (this==signined) {
			return 101;
		}
		if (this==started) {
			return 102;
		}
		if (this==fault) {
			return 103;
		}
		if (this==stoped) {
			return 104;
		}
		if (this==abandoned) {
			return 105;
		}
		if (this==related) {
			return 106;
		}
		return 0;
	}
	
	
	
	
	
}
