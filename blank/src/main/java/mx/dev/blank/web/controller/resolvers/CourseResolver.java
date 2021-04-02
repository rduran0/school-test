package mx.dev.blank.web.controller.resolvers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.entity.Course;
import mx.dev.blank.exceptions.ResourceNotFound;
import mx.dev.blank.service.CourseService;

@Slf4j
@RequiredArgsConstructor
public class CourseResolver implements HandlerMethodArgumentResolver {

	private final CourseService courseService;
	
@Override
public boolean supportsParameter(final MethodParameter parameter) {
	log.info("fullCourseId");
	    return parameter != null && parameter.getParameterType().equals(Course.class);
}

@SuppressWarnings("rawtypes")
@Override
public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
		final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
	 final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
	    final Map pathVariables =
	        (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

	    final String fullCourseId = (String) pathVariables.get("fullCourseId");
	    log.debug("fullCourseId: {}", fullCourseId);

	    if (StringUtils.isNotBlank(fullCourseId)) {
	      try {
	        final Course course = courseService.findCourse(Integer.parseInt(fullCourseId));
	        
	        return course;
	      } catch (final ResourceNotFound ex) {
	        log.warn("Not found {}", fullCourseId);
	      }
	    } else {
	      log.warn("Non course valid: {} - {}", fullCourseId, request.getRequestURI());
	    }

	    return null;
}
}