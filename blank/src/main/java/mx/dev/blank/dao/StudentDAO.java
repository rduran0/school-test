package mx.dev.blank.dao;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Student;
import org.springframework.validation.annotation.Validated;

@Validated
public interface StudentDAO {

  List<Student> getStudentsBySearchCriteria(
      String nameQuery,
      String uuidQuery,
      @NotNull Date rangeStart,
      @NotNull Date rangeEnd);
}
