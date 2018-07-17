package xjh.loveHome.IC.guard.service;

public enum WebGuardResponseCode {
	OpenSuccess,//开门成功
	OpenFail,//开门失败
	ServerBusy,//服务器忙
	Exception,//系统异常
	Nologin,//未登录
	NoFocus,//未关注
	NoAuthent;//未认证
	
	public int getCode() {
		if (this==WebGuardResponseCode.OpenSuccess) {
			return 1111;
		}
		if (this==OpenFail) {
			return 1112;
		}
		if (this==ServerBusy) {
			return 1113;
		}
		if (this==WebGuardResponseCode.Exception) {
			return 1114;
		}
		if (this==WebGuardResponseCode.Nologin) {
			return 1115;
		}
		if (this==WebGuardResponseCode.NoFocus) {
			return 1116;
		}
		if (this==NoAuthent) {
			return 1117;
		}
		return 0;
	}
	
	
}
