package xjh.loveHome.IC.guard;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import xjh.loveHome.IC.uitils.PageMode;
import xjh.loveHome.IC.uitils.PageModeName;
import xjh.loveHome.IC.uitils.WebGuardResponseCode;
import xjh.loveHome.IC.uitils.WebResponseJson;
/****
 * 分页数据查询，请求参数拦截器
 * ***/
public class PageDataQueryInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
			if (pageParametersCheck(inv)) {
				inv.invoke();
			}
	}
	
	
	/*****
	 * 请求接口必须有请求参数否则将立即返回请求参数错误
	 * ****/
	private boolean pageParametersCheck(Invocation invocation) {
			Controller controller=invocation.getController();
			if (controller!=null) {
				Integer pageNo=controller.getParaToInt(PageModeName.pageNo.getName());
				Integer rows=controller.getParaToInt(PageModeName.pageRow.getName());
				if (pageNo==null||rows==null){
					WebResponseJson	responseJson=new WebResponseJson();
					responseJson.setResponseCode(WebGuardResponseCode.RequsetError);
					controller.renderJson(responseJson.tojsion());
				}
				return true;
			}
			return false;
	}
	
}
