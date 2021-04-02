package mx.dev.blank;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.service.CourseService;
import mx.dev.blank.web.controller.resolvers.CourseResolver;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableWebMvc
public class SchoolWebMvcConfig implements WebMvcConfigurer {
	
	private final CourseService courseService;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		log.info("{}", resolvers.size());
		resolvers.add(new CourseResolver(courseService));
		log.info("{}", resolvers.size());
	}

}
