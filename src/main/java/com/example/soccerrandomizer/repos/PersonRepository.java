package com.example.soccerrandomizer.repos;

import com.example.soccerrandomizer.db.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findByUsername(String username);
    Person findDistinctByUsername(String username);
}
