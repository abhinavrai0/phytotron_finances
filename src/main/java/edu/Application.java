package edu;

import org.springframework.boot.SpringApplication;
import org.apache.catalina.filters.RemoteAddrFilter;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//public class Application extends SpringBootServletInitializer {
public class Application{
/*	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	 public FilterRegistrationBean remoteAddressFilter() {
	  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	  RemoteAddrFilter filter = new RemoteAddrFilter();
	  filter.setAllow("152\\.1\\.177\\.72|152\\.1\\.177\\.43|152\\.1\\.177\\.106");
	  filterRegistrationBean.setFilter(filter);
	  filterRegistrationBean.addUrlPatterns("/*");
	  return filterRegistrationBean;
	 }
}
