package model;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportDBAnnotation {

	public static void main(String[] args) {
		// 读取Hibernate总配置文件：hibernate.cfg.xml
		Configuration cfg = new AnnotationConfiguration().configure("hibernate.cfg.xml");
		// 定义执行计划（表输出）
		SchemaExport export = new SchemaExport(cfg);
		// 输出SQL脚本，并执行脚本
		export.create(true, true);
	}
	
}
