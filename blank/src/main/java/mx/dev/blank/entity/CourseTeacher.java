package mx.dev.blank.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "course_teacher")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseTeacher implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "course_id", nullable = false)
  private int courseId;

  @Column(name = "teacher_id", nullable = false)
  private int teacherId;

  @Column(name = "room_id", nullable = false)
  private int roomId;

  @Column(name = "start_time", nullable = false)
  private LocalTime startTime;

  @Column(name = "end_time", nullable = false)
  private LocalTime endTime;

  @Column(name = "day", nullable = false)
  private String day;

}
