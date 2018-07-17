package xjh.loveHome.IC.guard;

import java.util.Iterator;
import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RemoteDeviceInfoIntercept implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller=inv.getController();
		log.info("客户机：   {}", controller.getRequest().getRemoteHost()==null?"no":controller.getRequest().getRemoteHost());
		log.info("客户机IP：      {}", controller.getRequest().getRemoteAddr()==null?"0":controller.getRequest().getRemoteAddr());
		log.info("远程端口：      {}", controller.getRequest().getRemotePort());
		log.info("请求路径:          {}",controller.getRequest().getRequestURL().toString());
		System.out.println("客户机："+      controller.getRequest().getRemoteHost());
		Map<String, String[]>      para= controller.  getRequest().getParameterMap();
		for (Iterator<String> iterator = para.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			for (int i = 0; i < para.get(key).length; i++) {
				log.info("参数：    key :   {}     ----  value :  {}",key,para.get(key)[i]);
			}
		}
		inv.invoke();
	}
		
}
