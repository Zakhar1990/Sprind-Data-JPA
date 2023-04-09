package com.example.SpringDataJPA;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name= "Enrolment")
@Table(name = "enrolment")
public class Enrolment {
    @EmbeddedId
    private EnrolmentId id;
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_student_id_fk"
            )
    )
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id",
            foreignKey = @ForeignKey(
            name = "enrolment_course_id_fk"
    ))
    private Course course;
    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime localDateTime;

    public Enrolment() {
    }

    public Enrolment(Student student, Course course,LocalDateTime localDateTime) {
        this.student = student;
        this.course = course;
        this.localDateTime = localDateTime;
    }

    public Enrolment(EnrolmentId id,
                     Student student,
                     Course course,
                     LocalDateTime localDateTime) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.localDateTime = localDateTime;
    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
