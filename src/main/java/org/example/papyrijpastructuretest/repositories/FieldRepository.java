package org.example.papyrijpastructuretest.repositories;

import org.example.papyrijpastructuretest.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Resource, Long> {
}
