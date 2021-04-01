package mx.dev.blank.web.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CourseRequest {

	@NotBlank
	private final String name;
	@NotBlank
	private final String keycode;
}
