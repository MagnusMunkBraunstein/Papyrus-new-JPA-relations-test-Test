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
public class Field extends FileSystemItem {

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


    // --------------- Operations ---------------


    @Override
    public List<FileSystemItem> getChildren() {
        List<FileSystemItem> allChildren = new ArrayList<>(childrenFields);
        allChildren.addAll(childrenResources);
        return allChildren;
    }

    public void add(FileSystemItem item) {
        if (item instanceof Field) {
            childrenFields.add((Field) item);
        } else if (item instanceof Resource) {
            childrenResources.add((Resource) item);
        }
        item.setParent(this);
    }

    public void remove(FileSystemItem item) {
        if (item instanceof Field) {
            childrenFields.remove(item);
        } else if (item instanceof Resource) {
            childrenResources.remove(item);
        }
        item.setParent(null);
    }


    // --------------- ToString ---------------


}