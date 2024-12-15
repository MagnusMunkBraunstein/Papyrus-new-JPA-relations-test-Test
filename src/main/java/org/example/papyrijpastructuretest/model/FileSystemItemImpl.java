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
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class FileSystemItemImpl implements FileSystemItem {
    /* --------------- Implemented ---------------
         > FileSystemItem
             ---- var ----
                -- id
                -- name
                -- parent
                -- childrenFields
                -- childrenResources

             ---- operations ----
                -- getRoot()
                -- getParent()
                -- getChildren()

         > PathNavigator
                -- getPath()
                -- findByPath()
         > Validator
                -- validateHierarchy()
                -- hasUniqueName()
                -- isRoot()
          > Mover
                -- move()

        ---------------              ---------------                                                                        */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    // User var is not included in the FileSystemItem interface
        // only included in Field (and only initialized in Root field)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // parent_id is the column in the child table
    @JsonIgnore
    @ToString.Exclude // avoid infinite recursion
    protected Field parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // mappedBy is the field in the child class
    protected List<Field> childrenFields = new ArrayList<>();

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Resource> childrenResources = new ArrayList<>();


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
    public boolean isLeaf() {
        return getChildren().isEmpty();
    }

    public void setLeaf(boolean leaf) {
        if (leaf) {
            clearChildren();
        }
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
            child.propagateChange(change);
            int i = 0;
            int y= 0;
            if ( i == y) {
                System.out.println("i equals y");
            }
        }
    }

    public void display() {
        for (FileSystemItem child : getChildren()) {
            child.displayRecursive("-"); // recursive call
        }
    }
    public void displayRecursive(String indentation) {
        System.out.println(indentation + getName());
        for (FileSystemItem child : getChildren()) {
            child.displayRecursive(indentation + "-"); // recursive call
        }
    }


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

    public FileSystemItem getRoot() {
        return PathNavigationUtils.getRoot(this);
    }

    public String getPath() {
        return PathNavigationUtils.getPath(this);
    }

    public FileSystemItem findByPath(String path) {
        return PathNavigationUtils.findByPath(this, path);
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