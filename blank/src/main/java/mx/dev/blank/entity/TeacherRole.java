package mx.dev.blank.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "teacher_role")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeacherRole implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teacher_id", nullable = false)
  private Teacher teacher;

  @Column(name = "teacher_id", updatable = false, insertable = false)
  private String teacherId;


}
