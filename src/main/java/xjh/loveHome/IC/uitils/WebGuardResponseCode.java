package xjh.loveHome.IC.uitils;

public enum WebGuardResponseCode {
	OpenSuccess,//开门成功
	OpenFail,//开门失败
	ServerBusy,//服务器忙
	Exception,//系统异常
	Nologin,//未登录
	NoFocus,//未关注
	NoAuthent,//未认证
	RequsetError,//请求错误
	RequsetSuccess,
	RequsetFail;
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
		if (this==RequsetError) {
			return 1118;
		}
		if (this==RequsetFail) {
			return 1119;
		}
		if (this==RequsetSuccess) {
			return 1120;
		}
		
		
		return 0;
	}
	
	/******
	 * 获取code的含义
	 * *****/
	public String getMessage() {
		if (this==WebGuardResponseCode.OpenSuccess) {
			return "开门成功";
		}
		if (this==OpenFail) {
			return "开门失败";
		}
		if (this==ServerBusy) {
			return "服务器忙 code：1113";
		}
		if (this==WebGuardResponseCode.Exception) {
			return "服务器忙 code：1114";
		}
		if (this==WebGuardResponseCode.Nologin) {
			return "未登录";
		}
		if (this==WebGuardResponseCode.NoFocus) {
			return "未关注公众号";
		}
		if (this==NoAuthent) {
			return "未认证";
		}
		if (this==RequsetError) {
			return "请求错误";
		}
		if (this==RequsetFail) {
			return "请求失败";
		}
		if (this==RequsetSuccess) {
			return "请求成功";
		}
		
		return "";
	}
	
	 /***
	  * 响应状态
	  * **/
	public boolean getStatus() {
		if (this==WebGuardResponseCode.OpenSuccess) {
			return true;
		}
		if (this==OpenFail) {
			return false;
		}
		if (this==ServerBusy) {
			return  false;
		}
		if (this==WebGuardResponseCode.Exception) {
			return false;
		}
		if (this==WebGuardResponseCode.Nologin) {
			return false;
		}
		if (this==WebGuardResponseCode.NoFocus) {
			return false;
		}
		if (this==NoAuthent) {
			return false;
		}
		if (this==RequsetError) {
			return false;
		}
		if (this==RequsetFail) {
			return false;
		}
		if (this==RequsetSuccess) {
			return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
}
