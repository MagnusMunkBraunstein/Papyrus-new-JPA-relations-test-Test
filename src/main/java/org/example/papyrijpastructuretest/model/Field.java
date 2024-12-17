package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "resources")
public class Field extends FileSystemItemImpl {


    @OneToOne(mappedBy = "rootField", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore // avoid infinite recursion
    @ToString.Exclude
    private Field parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Field> childrenFields = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Resource> childrenResources = new ArrayList<>();

    // --------------- Constructors ---------------

    public Field(String name) {
        super();
        setName(name);
    }


    // children

    public FileSystemItem getChild(String name) {
        for (FileSystemItem child : getChildren()) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    public List<FileSystemItem> getChildren() {
        List<FileSystemItem> children = new ArrayList<>();
        children.addAll(childrenFields);
        children.addAll(childrenResources);

        return isLeaf() ? Collections.emptyList() : children;
    }

    public FileSystemItem add(FileSystemItem child) {
        if (child instanceof Field) {
            childrenFields.add((Field) child);
        } else if (child instanceof Resource) {
            childrenResources.add((Resource) child);
        }
        return this;
    }

    public void remove(FileSystemItem child) {
        if (child instanceof Field) {
            childrenFields.remove(child);
        } else if (child instanceof Resource) {
            childrenResources.remove(child);
        }
    }

    public void clearChildren() {
        childrenFields.clear();
        childrenResources.clear();
    }


    /* 4 Propagate a change to all children
                    -> i.e. a 'change' is a Function, which is passed to all children
        - utilises a Consumer to apply the change
            - this is a functional interface that takes a function instance and applies it to the object
            - in this case it helps by:
                - taking a FileSystemItem as an argument
                - and applying the change to it

         ex:
            Function upperCaseChange = item -> item.setName(item.getName().toUpperCase());
            propageChange(upperCaseChange);                                                             */

    public void propagateChange(Consumer<FileSystemItem> change) { // custom change function
        change.accept(this);
        for (FileSystemItem child : getChildren()) {
            if (!(child instanceof Field childField)) return;

            int i = 0;
            int y= 0;
            if ( i == y) {
                System.out.println("i equals y");
                System.out.println("chieldField " + childField);
            }
        }
    }

    public void display() {
        for (FileSystemItem child : getChildren()) {
            if (child instanceof Field childField) {
                childField.displayRecursive("-"); // recursive call
            }
        }
    }
    public void displayRecursive(String indentation) {
        System.out.println(indentation + getName());
        for (FileSystemItem child : getChildren()) {
            if (child instanceof Field childField) {
                childField.displayRecursive(indentation + "-"); // recursive call
            }
        }
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
}