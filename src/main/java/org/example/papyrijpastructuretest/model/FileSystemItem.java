package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;


@MappedSuperclass
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public abstract class FileSystemItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    String name;


}
