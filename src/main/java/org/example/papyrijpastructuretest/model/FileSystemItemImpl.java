package org.example.papyrijpastructuretest.model;

import lombok.*;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class FileSystemItemImpl extends FileSystemItem {

    // Core abstract methods that Field/Resource must implement
    abstract public List<FileSystemItem> getChildren();

    // Core properties all implementations need
    private String name;
    private Field parent;

    /* --------------- Implemented Operations ---------------
         > FileSystemItem
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
    /* --------------- Instance Methods --------------- */

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