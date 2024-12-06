package org.example.papyrijpastructuretest.repositories;

import org.example.papyrijpastructuretest.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByParentField(Field field);
}
