package org.example.papyrijpastructuretest.repositories;

import org.example.papyrijpastructuretest.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {}
