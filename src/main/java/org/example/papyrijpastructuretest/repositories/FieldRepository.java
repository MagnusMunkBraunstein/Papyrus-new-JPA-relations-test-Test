package org.example.papyrijpastructuretest.repositories;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {
    @Override
    Optional<Field> findById(Long aLong);

    @Query("select f from Field f where f.user.id = ?1")
    Field findByUser_Id(long id);
}
