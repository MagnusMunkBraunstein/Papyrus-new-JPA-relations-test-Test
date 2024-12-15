package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field extends FileSystemItemImpl {

    // --------------- User ---------------
    @OneToOne(mappedBy = "field")
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    private User user;


    // --------------- Children ---------------
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Field> childrenFields = new ArrayList<>();

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Resource> childrenResources = new ArrayList<>();


    // --------------- Constructors ---------------

    public Field(String name) {
        super();
        setName(name);
    }

}