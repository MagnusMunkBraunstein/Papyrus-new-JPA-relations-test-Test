package org.example.papyrijpastructuretest.repositories;

import org.example.papyrijpastructuretest.model.Field_Resource;
import org.example.papyrijpastructuretest.model.Field_ResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldResourceRepository extends JpaRepository<Field_Resource, Field_ResourceId > {}
