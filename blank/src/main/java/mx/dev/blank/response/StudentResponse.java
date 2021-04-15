package mx.dev.blank.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.dto.StudentDTO;

@RequiredArgsConstructor
@Getter
public class StudentResponse {

  private final List<StudentDTO> students;
  private final int size;
}
