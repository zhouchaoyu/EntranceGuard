package xjh.loveHome.IC.uitils;

import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/******
 * 接口安全监视器
 * *******/
public class SecurityMonitor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		if (requestParametersNotNUll(inv)) {
			inv.invoke();
		}
		
	}
	
	
	/*****
	 * 请求接口必须有请求参数否则将立即返回请求参数错误
	 * ****/
	private boolean requestParametersNotNUll(Invocation invocation) {
			Controller controller=invocation.getController();
			if (controller!=null) {
				Map<String, String[]> requestparaMap=controller.getRequest().getParameterMap();
				if (requestparaMap==null||requestparaMap.size()==0) {
					WebResponseJson	responseJson=new WebResponseJson();
					responseJson.setResponseCode(WebGuardResponseCode.RequsetError);
					controller.renderJson(responseJson.tojsion());
					return false;
				}
				return true;
			}
			return false;
	}
	
	
	

}
