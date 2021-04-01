package mx.dev.blank.web.controller.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mx.dev.blank.model.CourseDTO;

@Getter
@Setter
public class CourseResponse {

  private List<CourseDTO> courses; 
}
