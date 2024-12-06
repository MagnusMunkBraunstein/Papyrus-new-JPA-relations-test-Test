package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends FileSystem{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;

    @ManyToMany(mappedBy = "savedResources", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> users;

    @ManyToOne
    private Field field;
}
