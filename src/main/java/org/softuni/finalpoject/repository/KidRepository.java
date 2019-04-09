package org.softuni.finalpoject.repository;

import org.softuni.finalpoject.domain.entities.Kid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KidRepository extends JpaRepository<Kid, String> {
}
