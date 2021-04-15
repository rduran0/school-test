package mx.dev.blank.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
@EqualsAndHashCode(of = {"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  public User(final String name) {
    this.name = name;
  }

  public void updateName(final String name) {
    this.name = name;
  }
}
