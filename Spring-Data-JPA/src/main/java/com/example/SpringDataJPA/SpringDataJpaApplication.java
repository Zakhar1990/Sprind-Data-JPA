package com.example.SpringDataJPA;

import com.github.javafaker.Faker;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@SpringBootApplication
public class SpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }
@Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Faker faker = new Faker();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));
            student.addBook(new Book(LocalDateTime.now().minusDays(4),"Clean Code"));

            student.addBook(new Book(LocalDateTime.now(),"Think and Grow Rich"));

            student.addBook(new Book(LocalDateTime.now().minusYears(1),"Spring Data JPA"));

            StudentIdCard studentIdCard = new StudentIdCard("123456789", student);

            student.addEnrolment(new Enrolment(new EnrolmentId(1L,1L), student,new Course("Computer Science", "IT"),LocalDateTime.now()));
            student.addEnrolment(new Enrolment(new EnrolmentId(1L,2L),student,new Course("Spring Data JPA", "IT"),LocalDateTime.now().minusDays(18)));

            student.setStudentIdCard(studentIdCard);
            studentRepository.save(student);

            studentRepository.findById(1L)
                    .ifPresent(s -> {
                        System.out.println("fetch book lazy..");
                        List<Book> books = student.getBooks();
                        books.forEach(book -> {
                            System.out.println(
                                    s.getFirstName() + " borrowed" + book.getBookName());
                        });
                    });
        };

    }


    private static void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i <= 20; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            studentRepository.save(student);

        }
    }
}
