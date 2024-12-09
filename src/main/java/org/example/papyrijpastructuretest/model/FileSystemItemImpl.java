package org.example.papyrijpastructuretest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class FileSystemItemImpl extends FileSystemItem {

    private Field parent;


    // --------------- Methods ---------------

    public FileSystemItem getRoot() {
        FileSystemItem current = this;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current;
    }

    abstract void update(Resource child);
    abstract void update(Field child);

    // --------------- Display ---------------

    public void display() {
        System.out.print(this.name);
        String indentation = "-";
        System.out.println();
        for (Field field : childrenFields) {
            System.out.print(indentation);
            field.display();
        }
        for (Resource resource : childrenResources) {
            System.out.print(indentation);
            resource.display();
        }
    }


}
