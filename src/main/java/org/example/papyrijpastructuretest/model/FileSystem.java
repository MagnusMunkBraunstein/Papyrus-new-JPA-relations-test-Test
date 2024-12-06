package org.example.papyrijpastructuretest.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
abstract class FileSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    String name;
}
