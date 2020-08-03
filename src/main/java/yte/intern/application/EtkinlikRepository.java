package yte.intern.application;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtkinlikRepository extends JpaRepository<Etkinlik,Long> {
Etkinlik findByName(String name);



void deleteById(Long id);

void deleteByName(String name);



void deleteByNameAndStartAndEnd(String name, String start, String end);



//Optional<Etkinlik> findById(Long id); 

}
