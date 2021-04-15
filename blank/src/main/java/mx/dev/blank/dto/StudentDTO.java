package mx.dev.blank.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StudentDTO {

  private final String name;
  private final String lastName;
  private final int age;

  public static StudentDTO newStudent(final String name, final String lastName, final int age) {
    return new StudentDTO(name, lastName, age);
  }

  public static List<StudentDTO> listForTest(final int size) {
    final String nameTemplate = "Student-%d";
    final String lastNameTemplate = "Last-%d";
    int ageCounter = 18;

    final List<StudentDTO> students = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      final int current = i + 1;
      students.add(newStudent(
          String.format(nameTemplate, current),
          String.format(lastNameTemplate, current),
          ++ageCounter));
    }

    return students;
  }
}
