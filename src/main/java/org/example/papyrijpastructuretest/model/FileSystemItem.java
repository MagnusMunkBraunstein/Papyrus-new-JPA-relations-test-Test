package org.example.papyrijpastructuretest.model;

import java.util.List;
import java.util.function.Consumer;


public interface FileSystemItem {

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

    long getId();

    String getName();

    Field getParent();
    void setParent(Field newParent);

    List<FileSystemItem> getChildren();

    // --------------- Main Operations ---------------

    boolean isLeaf();

    // --------------- Util Methods ---------------
    FileSystemItem search(String name);
    FileSystemItem search(String name, int depthlimit);

}



