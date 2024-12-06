package org.example.papyrijpastructuretest.repositories;

import org.example.papyrijpastructuretest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
