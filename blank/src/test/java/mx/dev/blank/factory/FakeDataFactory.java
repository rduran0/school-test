package mx.dev.blank.factory;

import mx.dev.blank.entity.Student;
import mx.dev.blank.entity.User;
import mx.dev.blank.service.StudentService;

public class FakeDataFactory {

  public static User user() {
    return user("anyone");
  }

  public static User user(final String name) {
    return new User(name);
  }

  public static Student student(final String name) {
    return student(name, StudentService.UNSPECIFIED_NAME, StudentService.UNSPECIFIED_NAME);
  }

  public static Student student(final String name, final String firstSurname, final String seondSurname) {
    return Student.forTest(name, firstSurname, seondSurname);
  }
}
