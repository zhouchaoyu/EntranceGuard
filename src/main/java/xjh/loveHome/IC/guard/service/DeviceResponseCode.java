package xjh.loveHome.IC.guard.service;
/***
 * 
 * 设备响应码：  用于回复硬件设备的响应码
 * *****/
public enum DeviceResponseCode {
	RequsetError,//请求错误
	ParaError,//参数错误
	Stop,//停止使用
	Recover,//恢复使用
	OpenDoor,//开门
	CloseDoor,//关门
	Exception;//异常
	public int getCode(){
		if (this==RequsetError) {
			return 1101;
		}
		if (this==ParaError) {
			return 1102;
		}
		if (this==Stop) {
			return 1103;
		}
		if (this==DeviceResponseCode.Recover) {
			return 1104;
		}
		if (this==DeviceResponseCode.OpenDoor) {
			return 1105;
		}
		if (this==DeviceResponseCode.CloseDoor) {
			return 1106;
		}
		if (this==DeviceResponseCode.Exception) {
			return 1107;
		}
		return 0;
	}
	
	
	
	
	
}
