package org.casey.common.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @ClassName MybatisPlusGenerator
 * @Author Fu Kai
 * @Description Mybatis-plus代码生成器
 * @Date 2020/11/17
 */

public class MybatisPlusGenerator {
    /**
     * 数据源
     */
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "2168230078";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://192.168.153.128:3306/cloud?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=GMT%2B8";

    /**
     * 每次运行必改, 如数据库有表ss_user, 必须改前缀为ss_不然生成会SsUser
     * private static final String TABLE_PREFIX = "mybatis_"; 有前缀
     * private static final String TABLE_PREFIX = "user"; 无前缀
     */

    private static final String TABLE_PREFIX = "cloud_";
    /**
     * 需要生成的表名, 建议单个单个表生成
     * private static final String[] TABLE_NAMES = new String[]{"mybatis_user"}; 有前缀
     * private static final String[] TABLE_NAMES = new String[]{"user"}; 无前缀
     */
    private static final String[] TABLE_NAMES = new String[]{"cloud_account", "cloud_account_role", "cloud_role", "cloud_role_permission", "cloud_permission"};

    /**
     * 项目目录, 绝对路径
     */
    private static final String OUT_PATH = "F:/repository/java/spring-cloud-h/cloud-account-service/src/main/java";

    /**
     * 包名, 重要, mapper.xml中会以这个为路径
     */
    private static final String PACKAGE_NAME = "org.casey.cloudaccountservice";

    /**
     * 作者
     */
    private static final String AUTHOR = "Fu Kai";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("确定要生成表: ");
        for (String each : TABLE_NAMES) {
            System.out.print(each + ", ");
        }
        System.err.println("现存在的代码将会被覆盖!!! 在输入\"yes 1t7v93\"后执行\n");
        String confirm = in.nextLine();
        in.close();
        if (!"yes 1t7v93".equals(confirm)) {
            System.err.println("取消了操作");
            return;
        }
        MybatisPlusGenerator mbg = new MybatisPlusGenerator();
        // 自定义配置
        InjectionConfig injectionConfig = mbg.getInjectionConfig();

        // 配置数据源
        DataSourceConfig dataSourceConfig = mbg.getDataSourceConfig();

        // 策略配置
        StrategyConfig strategyConfig = mbg.getStrategyConfig(TABLE_NAMES);

        // 全局变量配置
        GlobalConfig globalConfig = mbg.getGlobalConfig();

        // 包名配置
        PackageConfig packageConfig = mbg.getPackageConfig();

        // 配置模板
        TemplateConfig templateConfig = mbg.getTemplateConfig();
        // 执行
        new AutoGenerator()
                .setCfg(injectionConfig)
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                // 设置freemarker引擎
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setTemplate(templateConfig)
                .execute();
    }

    /**
     * 配置的模板
     */
    private TemplateConfig getTemplateConfig() {
        return new TemplateConfig()
                .setController("/templates/controller.java")
                .setService("/templates/service.java")
                .setServiceImpl("/templates/serviceImpl.java")
                .setEntity("/templates/entity.java")
                .setMapper("/templates/mapper.java")
                .setXml("/templates/mapper.xml");
    }

    private static String generateDate() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(localDate);
    }

    /**
     * 自定义配置
     */
    private InjectionConfig getInjectionConfig() {
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            // 向模板注入变量
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("date", generateDate());
                setMap(map);
            }
        };
        // 生成.xml文件
        // List<FileOutConfig> fileOutConfigList = new ArrayList<>();
        // FileOutConfig xml = new FileOutConfig("templates/mapper.xml.ftl") {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         return "F:/repository/java/spring-boot/memo-mybatis/src/main" + "/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
        //     }
        // };
        // fileOutConfigList.add(xml);
        // injectionConfig.setFileOutConfigList(fileOutConfigList);
        return injectionConfig;
    }

    /**
     * 设置包名
     */
    private PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent(PACKAGE_NAME)
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setEntity("entity")
                .setMapper("mapper")
                .setXml("mapper.xml");
    }

    /**
     * 全局配置
     */
    private GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(AUTHOR)
                // 作者
                .setBaseColumnList(true)
                // XML ResultMap
                .setBaseResultMap(true)
                // 开启 activeRecord 模式
                .setActiveRecord(false)
                .setOutputDir(OUT_PATH)
                // 生成后打开目录
                .setOpen(false)
                // swagger2
                .setSwagger2(true)
                // 是否覆盖文件
                .setFileOverride(true)
                // XML 二级缓存
                .setEnableCache(false)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setControllerName("%sController")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper");
        return globalConfig;
    }

    /**
     * 策略配置
     */
    private StrategyConfig getStrategyConfig(String... tableNames) {
        return new StrategyConfig()
                // 全局大写命名
                .setCapitalMode(true)
                // 表名、字段名、是否使用下划线命名(默认 false)
                .setEntityLombokModel(true)
                // RestController风格
                .setRestControllerStyle(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames)
                // 修改表前缀
                .setTablePrefix(TABLE_PREFIX);
    }

    /**
     * 配置数据源
     */
    private DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .setDriverName(DRIVER)
                .setUrl(URL);
    }
}