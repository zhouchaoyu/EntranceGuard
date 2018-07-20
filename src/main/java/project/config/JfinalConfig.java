package project.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.template.Engine;

import lombok.extern.java.Log;
import xjh.loveHome.IC.guard.CacheLoader;
import xjh.loveHome.IC.guard.route.GuardRoute;
import xjh.loveHome.IC.mode._MappingKit;

@Log
public class JfinalConfig extends JFinalConfig {
	public static Map<String, DruidPlugin> createC3p0PluginList() throws Exception {

		Map<String, DruidPlugin> c3p0Plugins = new HashMap<>();   
		/*
		 * DruidPlugin druidPlugin_B = new DruidPlugin(PropKit.get("jdbcUrl_B"),
		 * PropKit.get("user_B"), PropKit.get("password_B").trim());
		 * c3p0Plugins.put("druidPlugin_B", druidPlugin_B);
		 */
		DruidPlugin druidPlugin_A = new DruidPlugin(PropKit.get("jdbcUrl_A"), PropKit.get("user_A"),
				PropKit.get("password_A").trim());
		c3p0Plugins.put("druidPlugin_A", druidPlugin_A);
		return c3p0Plugins;
	}

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
		PropKit.use("jfinalPropert.properties"); 
	}

	@Override
	public void configRoute(Routes me) {
		 me.add(new GuardRoute());//门禁系统路由
	}

	@Override
	public void configPlugin(Plugins me) {
		try {
			Map<String, DruidPlugin> druidPlugins = createC3p0PluginList();
			for (Iterator<String> iterator = druidPlugins.keySet().iterator(); iterator.hasNext();) {
				String druidPluginName = (String) iterator.next();
				me.add(druidPlugins.get(druidPluginName));
				// 配置ActiveRecord插件
				ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPluginName,
						druidPlugins.get(druidPluginName));
				arp.setShowSql(true); 
				me.add(arp);
				_MappingKit.mapping(arp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 配置redis
		RedisPlugin redisPlugin = new RedisPlugin("myRedis", "127.0.0.1", 6379);
		//redisPlugin.setSerializer(new StringRedisSerializer());
		me.add(redisPlugin);
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configHandler(Handlers me) {
		// me.add(new MyHandler());
	}
	@Override
	public void afterJFinalStart() {
		// TODO Auto-generated method stub
		super.afterJFinalStart();
		CacheLoader loader=CacheLoader.CACHE_LOADER;
		loader.initLoad(null);
		log.info("Jfnal 启动成功");
	}

	@Override
	public void beforeJFinalStop() {
		super.beforeJFinalStop();
		
		log.info("Jfnal 已关闭");
	}

	@Override
	public void configEngine(Engine me) {
	

	}

}
