package edu;

import org.springframework.boot.SpringApplication;
import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
/*	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}




	/*@Bean
	 public FilterRegistrationBean remoteAddressFilter() {
	  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	  RemoteAddrFilter filter = new RemoteAddrFilter();
	 // filter.setAllow("127.0.0.1");
//	  filter.setAllow("127\\.0\\.0\\.1");
	  System.out.println(filter);
//	  filter.setAllow("152\\.1\\.177\\.101");
	  filterRegistrationBean.setFilter(filter);
	  filterRegistrationBean.addUrlPatterns("/*");
	  return filterRegistrationBean;
	 }*/
}
