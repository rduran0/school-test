package mx.dev.blank.dao;

import java.util.List;
import javax.validation.constraints.Min;
import mx.dev.blank.model.CourseRoomDTO;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TeacherDAO {

  List<CourseRoomDTO> getAssignedCourseRoomsByTeacherId(@Min(1) int id);
}
