package io.github.tuliochamorra.model.repository;
import io.github.tuliochamorra.model.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeopleRepository extends JpaRepository<People, Integer> {

}
