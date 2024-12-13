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


    /* --------------- Main Operations ---------------
         - move()
            -- move within root-field
         - rename()
         - getPath()
            -- path from root
         - Validate()
         - ValidateHierarchy()
         - isRoot()
         - hasUniqueName()
        ---------------              ---------------                                                                        */

    public void move(Field newParent) {
        if (isRoot()) return;
        getParent().remove(this);

        // fiend newParent in root hierarchy
        Field root = (Field) getRoot();


    }

    public boolean validate() {
        if (isRoot())   return true;
        if (getChildren().isEmpty()) return false;
        return parent != null
                && hasUniqueName();
    }

    protected boolean validateHierarchy() {
        return isRoot() || (!getChildren().isEmpty() && hasUniqueName());
    }

    public boolean isRoot() {
        return user != null
                && getParent() == null
                && "root".equals(getName());
    }

    // --------------- helper of Main Operations

        /*
         *  rule: fields has unique name *at their level*
         *
         * -> Checks if the current field has a unique name among its siblings.
         *
         * The uniqueness is determined at the same hierarchical level (i.e., among the children of the same parent).
         *
         * @return {@code true} if the field is the root or if there are no other fields with the same name at the same level;
         *         {@code false} otherwise.
         */

    private boolean hasUniqueName() {
        if (isRoot()) return true;
        return getParent().getChildren().stream()
                .filter(item -> item instanceof Field)
                .noneMatch(item -> item.getName().equals(getName()));
    }

    // --------------- CRUD ---------------
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