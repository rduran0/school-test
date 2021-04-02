package mx.dev.blank.web.controller.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequest {

	@NotBlank
	private String name;
	@NotBlank
	private String keycode;
}
