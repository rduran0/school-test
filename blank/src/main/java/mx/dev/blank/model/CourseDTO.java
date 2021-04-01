package mx.dev.blank.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class CourseDTO {

	private final long id;
	private final String name;
	private final String keycode;
	private final boolean isMathCourse;
	
	@Setter
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer numberTeachers;
}
