package yte.intern.application;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtkinlikRepository extends JpaRepository<Etkinlik,Long> {
Etkinlik findByName(String name);



void deleteById(Long id);

void deleteByName(String name); }
