package mx.dev.blank.web.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseFilterRequest {
	
	private String keycode;
	private String value;
}
