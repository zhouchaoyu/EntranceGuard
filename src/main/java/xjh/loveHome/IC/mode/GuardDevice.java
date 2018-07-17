package xjh.loveHome.IC.mode;

import xjh.loveHome.IC.guard.service.DeviceStateCode;
import xjh.loveHome.IC.mode.base.BaseGuardDevice;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class GuardDevice extends BaseGuardDevice<GuardDevice> {
	public static final GuardDevice dao = new GuardDevice().dao();
	/***
	 * 根据设备编号查询设备是否已注册
	 * ***/
	public boolean isSignined(String no) {
		if (getDeviceByNo(no)!=null) {
			return true;
		}
		return false;
	}
	
	
	public Integer getState(String no) {
		getDeviceByNo(no);
		return null;
	}
	
	public GuardDevice getDeviceByNo(String no) {
		String sql="select * from guard_device where no=?";
		return findFirst(sql, no);
	}
	
}