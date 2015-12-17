package demo.thymeleaf.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesiredRepository extends JpaRepository<DesiredThing, Integer> {

}
