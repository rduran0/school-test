package mx.dev.blank.dao;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import mx.dev.blank.entity.Teacher;
import mx.dev.blank.model.CourseRoomDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TeacherDAO {

  List<CourseRoomDTO> getAssignedCourseRoomsByTeacherId(@Min(1) int id);

  Teacher getByUuid(@NotBlank String uuid);
}
