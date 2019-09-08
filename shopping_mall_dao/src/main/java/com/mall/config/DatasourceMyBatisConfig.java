package com.mall.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * @author : Vander
 * @date : 2018-08-14
 * @description ：
 */
@Configuration
@MapperScan(basePackages = {"com.mall.mapper"},
        sqlSessionFactoryRef = "sqlSessionFactory")
@PropertySource(value = {"classpath:db/db.properties"},
        ignoreResourceNotFound = true, encoding = "UTF-8")
public class DatasourceMyBatisConfig {

    @Value("${datasource.mapperLocations}")
    private String mapperLocations;

    @Value("${mybatis.configLocation}")
    private String mybatisConfigLocations;

    /**
     * 使用@ConfigurationProperties(prefix = "datasource.mall")会有问题
     * 会发现值没有被Set进去
     *
     * @param jdbcUrl
     * @param username
     * @param password
     * @param maxActive
     * @return
     */
    @Bean(name = "dataSource")
    @Primary
    public static DataSource druidDataSource(
            @Value("${datasource.mall.jdbc-url}") String jdbcUrl,
            @Value("${datasource.mall.username}") String username,
            @Value("${datasource.mall.password}") String password,
            @Value("${datasource.mall.max-active}") int maxActive) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcUrl);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(maxActive);
        return druidDataSource;
    }

    /**
     * 充值-SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dataSource") DataSource dataSource
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        mapperLocations = "classpath:mybatis/mapper/*.xml";
        mybatisConfigLocations = "classpath:/mybatis/mybatis.xml";
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(mybatisConfigLocations));
        //设置插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{});
        return sqlSessionFactoryBean.getObject();
    }

}
