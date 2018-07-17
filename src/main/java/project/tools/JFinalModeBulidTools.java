package project.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.BaseModelGenerator;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.druid.DruidPlugin;

import lombok.extern.slf4j.Slf4j;
import project.config.JfinalConfig;

/**
 * 
 * @description 运行该工具类以从数据库动态构建mode
 * 
 */

@Slf4j
public class JFinalModeBulidTools {

	public static List<DataSource> getDataSource() throws Exception {
		Prop prop = PropKit.use("jfinalPropert.properties");
		List<DataSource> dataSources = new ArrayList<>();
		Map<String, DruidPlugin> c3p0Plugins = JfinalConfig.createC3p0PluginList();
		for (Iterator iterator = c3p0Plugins.values().iterator(); iterator.hasNext();) {
			DruidPlugin c3p0Plugin = (DruidPlugin) iterator.next();
			c3p0Plugin.start();
			dataSources.add(c3p0Plugin.getDataSource());
		}

		return dataSources;
	}

	public static void main(String[] args) throws Exception {
		String modePath = PathKit.getWebRootPath() + "/src/main/java/xjh/loveHome/IC/mode";
		String baseModePath = modePath + "/base";
		BaseModelGenerator baseModelGenerator = new BaseModelGenerator("xjh.loveHome.IC.mode.base", baseModePath);
		ModelGenerator modelGenerator = new ModelGenerator("xjh.loveHome.IC.mode", "xjh.loveHome.IC.mode.base", modePath);
		
		List<DataSource> dataSources=getDataSource();
		for (Iterator iterator = dataSources.iterator(); iterator.hasNext();) {
			DataSource dataSource = (DataSource) iterator.next();
			
			// 创建生成器
			Generator gernerator = new Generator(dataSource, baseModelGenerator, modelGenerator);
			// 添加不需要生成的表名
			gernerator.addExcludedTable("adv");
			// 设置是否在 Model 中生成 dao 对象
			gernerator.setGenerateDaoInModel(true);
			// 设置是否生成字典文件
			gernerator.setGenerateDataDictionary(false);
			// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为
			// "User"而非 OscUser
			gernerator.setRemovedTableNamePrefixes("t_");
			// 生成
			gernerator.generate();
		}
		
		
	}
}
