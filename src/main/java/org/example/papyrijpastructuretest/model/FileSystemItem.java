package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;


@MappedSuperclass
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public abstract class FileSystemItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    protected Field parent;

    /* --------------- Operations ---------------
        1 getChildren()
        2 getRoot()
        3 search()
        4 propagateChange()
        5 display()
        6 validateHierarchy()
       ---------------              ---------------                                                  */

    // 1 Abstract method to define recursive hierarchy
    public abstract List<FileSystemItem> getChildren();

    // 2 Common methods to get root or propagate actions
    public FileSystemItem getRoot() {
        FileSystemItem current = this;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current;
    }

    // 3 Search
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

    // 3.1 +depth limit
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
}
