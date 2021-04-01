package mx.dev.blank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.service.CourseService;
import mx.dev.blank.web.controller.resolvers.CourseResolver;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableWebMvc
public class SchoolWebMvcConfig extends WebMvcConfigurerAdapter{
	
	private final CourseService courseService;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		log.info("Test test 222");
		log.info("{}", resolvers.size());
		CourseResolver resolver = new CourseResolver(courseService);
		resolvers.add(resolver);
		log.info("{}",resolver.supportsParameter(null));
		log.info("{}", resolvers.size());
	}

}
