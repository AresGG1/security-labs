package com.smaha.security.student;

/*
    @author taras
    @project IntelliJ IDEA
    @class Student
    @version 1.0.0
    @since 29.03.25 - 21.39
*/

import lombok.*;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {
    @Id
    private String id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    public Student(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;

        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
