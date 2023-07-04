package com.mega.vhplayer.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		// 아래 폴더들에 내용 추가됐을 때 1초마다 갱신!
		// 1. Home
//		reg.addResourceHandler("/assets/css/**")
//		.addResourceLocations("classpath:/static/assets/css/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/favi/**")
//		.addResourceLocations("classpath:/static/assets/favi/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/font/**")
//		.addResourceLocations("classpath:/static/assets/font/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/webfonts/**")
//		.addResourceLocations("classpath:/static/assets/webfonts/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/images/**")
//		.addResourceLocations("classpath:/static/assets/images/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/img/**")
//		.addResourceLocations("classpath:/static/assets/img/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/js/**")
//		.addResourceLocations("classpath:/static/assets/js/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		// 2. Admin
//		reg.addResourceHandler("/assets/adminassets/css/**")
//		.addResourceLocations("classpath:/static/assets/adminassets/css/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/adminassets/images/**")
//		.addResourceLocations("classpath:/static/assets/adminassets/images/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/assets/adminassets/js/**")
//		.addResourceLocations("classpath:/static/assets/adminassets/js/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		// 3. Player
//		reg.addResourceHandler("/css/**")
//		.addResourceLocations("classpath:/static/css/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/images/**")
//		.addResourceLocations("classpath:/static/images/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/js/**")
//		.addResourceLocations("classpath:/static/js/")
//		.setCachePeriod(60 * 60 * 24 * 365);
//		
//		reg.addResourceHandler("/songs/**")
//		.addResourceLocations("classpath:/static/songs/")
//		.setCachePeriod(60 * 60 * 24 * 365);
	}
}
