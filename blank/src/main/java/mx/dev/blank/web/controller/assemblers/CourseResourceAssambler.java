package mx.dev.blank.web.controller.assemblers;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import mx.dev.blank.entity.Course;
import mx.dev.blank.model.CourseDTO;
import mx.dev.blank.service.CourseService;

@RequiredArgsConstructor
public class CourseResourceAssambler {

	@Component
	@RequiredArgsConstructor
	public static class Factory {
		
		private final CourseService courseService;
		
		 public CourseResourceAssambler create(final List<String> expand) {
			      return new CourseResourceAssambler(
			          courseService,
			          expand);
			    }
	}
	
	private final CourseService courseService;
	private final List<String> expand;
	
	public CourseDTO toDto(final Course course) {
		final CourseDTO dto = new CourseDTO(course.getId(), course.getName(), course.getKeycode(), course.getKeycode().startsWith("MAT"));
		
		if(expand.contains("teachers")) {
			dto.setNumberTeachers(courseService.countTeachersByCourseId(course.getId()));
		}
		
		return dto;
	}
}
