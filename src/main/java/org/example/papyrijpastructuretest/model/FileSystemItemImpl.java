package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
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
        1 getChildren()
        2 getRoot()
        3 search()
        4 propagateChange()
        5 display()
        6 validateHierarchy()
       ---------------              ---------------                                                  */


    public List<FileSystemItem> getChildren() {
        List<FileSystemItem> children = new ArrayList<>();
        children.addAll(childrenFields);
        children.addAll(childrenResources);
        return children;
    }

    public FileSystemItem search(String name) {
        // Check the current item's name
        if (this.getName().equals(name)) {
            return this;
        }

        // Recursively search in children
        for (FileSystemItem child : this.getChildren()) {
            FileSystemItem result = child.search(name); // recursive call
            if (result != null) {
                return result; // Return as soon as a match is found
            }
        }

        // No match found
        return null;
    }
    public FileSystemItem search(String name, int depthLimit) {
        if (depthLimit < 0) {
            return null;
        }
        if (this.getName().equals(name)) {
            return this;
        }
        for (FileSystemItem child : this.getChildren()) {
            FileSystemItem result = child.search(name, depthLimit - 1);
            if (result != null) {
                return result;
            }
        }
        return null;
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

    public void display(String indentation) {
        System.out.println(indentation + getName());
        for (FileSystemItem child : getChildren()) {
            child.display(indentation + "-"); // recursive call
        }
    }


    /* --------------- Util Methods --------------- */

    public void move(Field newParent) {
        if (ValidationUtils.isValidMove(this, newParent)) {
            MovementUtils.executeMove(this, newParent);
        }
    }

    public String getPath() {
        return PathNavigationUtils.getPath(this);
    }

    public boolean validate() {
        return ValidationUtils.validateHierarchy(this);
    }

    public boolean isRoot() {
        return ValidationUtils.isRoot(this);
    }

    public boolean hasUniqueName() {
        return ValidationUtils.hasUniqueName(this);
    }


}