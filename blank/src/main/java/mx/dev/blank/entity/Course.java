package mx.dev.blank.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mx.dev.blank.web.controller.request.CourseRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "keycode", nullable = false)
  private String keycode;

  public Course(final String name, final String keycode) {
	  this.name = name;
	  this.keycode = keycode;
  }
  
  public void update(final CourseRequest request) {
	  this.name = request.getName();
	  this.keycode = request.getKeycode();
  }
}
