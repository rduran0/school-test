package mx.dev.blank.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="grade")
@EqualsAndHashCode(of={"id"})
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Grade implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    @Column (name = "id", nullable = false)
    private Integer  id;

    @Column(name="score",nullable = false)
    private Float score;

    @Column(name="registry_day",nullable = false)
    private Date registry_day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_teacher_id", nullable = false)
    private CourseTeacher courseTeacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

}
