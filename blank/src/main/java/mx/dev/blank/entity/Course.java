package mx.dev.blank.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "keycode", nullable = false)
  private String keycode;

  @ManyToOne(fetch = FetchType.LAZY)
  @Column(name="student_id",nullable = false)
  private Student studentUUID;
}
