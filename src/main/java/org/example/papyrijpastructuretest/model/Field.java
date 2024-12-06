package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field extends FileSystemItem {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field", cascade = CascadeType.ALL)
    private List<Resource> childrenResources = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Field parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Field> childrenFields = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;



    public void add(FileSystemItem item) {
        if (item instanceof Resource) {
            childrenResources.add((Resource) item);
        } else if (item instanceof Field) {
            childrenFields.add((Field) item);
        }
    }


}