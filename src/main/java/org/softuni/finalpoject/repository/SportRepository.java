package org.softuni.finalpoject.repository;

import org.softuni.finalpoject.domain.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, String> {
}
