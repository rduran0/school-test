CREATE TABLE grade (
    id int(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    score float(3)NOT NULL,
    course_teacher_id int(20) UNSIGNED NOT NULL,
    student_id int(20) UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT student_course_teacher_id_fk FOREIGN KEY (course_teacher_id) REFERENCES course_teacher (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT student_id_fk FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;