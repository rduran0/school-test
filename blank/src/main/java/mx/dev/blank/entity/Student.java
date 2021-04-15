package mx.dev.blank.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "uuid", nullable = false)
  private String uuid;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "first_surname", nullable = false, columnDefinition = "TEXT")
  private String firstSurname;

  @Column(name = "second_surname", columnDefinition = "TEXT")
  private String secondSurname;

  @Temporal(TemporalType.DATE)
  @Column(name = "birthday", nullable = false)
  private Date birthday;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_teacher_id")
  @Setter
  private CourseTeacher assignedCourse;

  /**
   * Visible only for testing
   */
  public static Student forTest(final String name, final String firstSurname, final String secondSurname) {
    final Student student = new Student();
    student.name = name;
    student.firstSurname = firstSurname;
    student.secondSurname = secondSurname;

    return student;
  }
}
