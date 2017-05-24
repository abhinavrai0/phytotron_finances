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




	/*@Bean
	 public FilterRegistrationBean remoteAddressFilter() {
	  FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	  RemoteAddrFilter filter = new RemoteAddrFilter();
	 // filter.setAllow("127.0.0.1");
//	  filter.setAllow("127\\.0\\.0\\.1");
	  System.out.println(filter);
//	  filter.setAllow("127\\.\\d+\\.\\d+\\.\\d+");
//	  filter.setAllow("127\\.\\d+\\.\\d+\\.\\d+|::1|0:0:0:0:0:0:0:1");

//	  filter.setAllow("10\\.\\d+\\.\\d+\\.\\d+|71\\.69\\.\\d+\\.\\d+|192\\.168\\.\\d+\\.\\d+|2606:a000:4dc9:e500:258a:8709:8650:ff80");
//	  filter.setAllow("192\\.168\\.0\\.17");
//	  filter.setAllow("152\\.1\\.177\\.72|152\\.1\\.177\\.43|152\\.1\\.177\\.106");
	  filter.setAllow("10\\.255\\.247\\.35|127\\.\\d+\\.\\d+\\.\\d+");
//	  <param-value>127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1</param-value>
	  filterRegistrationBean.setFilter(filter);
	  filterRegistrationBean.addUrlPatterns("/*");
	  return filterRegistrationBean;
	 }*/
}
