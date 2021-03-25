package mx.dev.blank.entity;

import javax.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.Type;

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

  @Column(name = "first_surname", nullable = false, columnDefinition = "text")
  private String firstSurname;

  @Column(name = "second_surname", columnDefinition = "text")
  private String secondSurname;

  @Temporal(TemporalType.DATE)
  @Column(name = "birthday", nullable = false)
  private Date birthday;

}
