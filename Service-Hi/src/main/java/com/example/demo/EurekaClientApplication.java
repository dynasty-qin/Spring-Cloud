package com.example.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;
import javax.sql.DataSource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@MapperScan("com.example.demo.mapper")
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://47.104.240.128:3306/hr?useSSL=false");
		dataSource.setUsername("root");//用户名
		dataSource.setPassword("harry123123");//密码
		dataSource.setInitialSize(10);
		dataSource.setMaxActive(50);
		dataSource.setMinIdle(0);
		dataSource.setMaxWait(60000);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(false);
		return dataSource;
	}
}
