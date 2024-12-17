package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.papyrijpastructuretest.utils.FileSystemUtils;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;

import java.util.*;
import java.util.function.Consumer;

@Data
@EqualsAndHashCode(of = "id")

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "filesystem_items")
public abstract class FileSystemItemImpl implements FileSystemItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, unique = true)
    private String name;


    // User var is not included in the FileSystemItem interface
        // only included in Field (and only initialized in Root field)

    protected Field parent;

    /* --------------- Operations ---------------
        - isLeaf()
        - getRoot()
        - getParent()
        - getChildren()
            ---
        - search()
        - propagateChange()
        - display()
            ---
        - move()
        - getPath()
        - validate()
        - isRoot()
        - hasUniqueName()
       ---------------              ---------------                                                  */

    // leaf
    public abstract boolean isLeaf();

    public abstract List<FileSystemItem> getChildren();

    /* --------------- Util Methods --------------- */

    public FileSystemItem search(String name) {
        return FileSystemUtils.search(this, name);
    }

    public FileSystemItem search(String name, int depthLimit) {
        return FileSystemUtils.search(this, name, depthLimit);
    }

    public void move(Field newParent) {
        if (ValidationUtils.isValidMove(this, newParent)) {
            MovementUtils.executeMove(this, newParent);
        }
    }

    public Field getRoot() {
        return PathNavigationUtils.getRoot(this);
    }

    public String getPath() {
        return PathNavigationUtils.getPath(this);
    }

    public FileSystemItem findByPath(String path) {
        return PathNavigationUtils.findByPath(this.getRoot(), path);
    }

    public boolean validate() {
        return ValidationUtils.validate(this);
    }

    public boolean isRoot() {
        return ValidationUtils.isRoot(this);
    }

    public boolean hasUniqueName() {
        return ValidationUtils.hasUniqueName(this);
    }

}