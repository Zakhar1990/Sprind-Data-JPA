package com.example.SpringDataJPA;

import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository
        extends CrudRepository <StudentIdCard,Long> {
}
