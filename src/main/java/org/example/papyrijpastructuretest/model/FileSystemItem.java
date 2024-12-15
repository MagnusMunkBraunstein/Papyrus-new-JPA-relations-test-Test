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

    FileSystemItem getParent();

    List<FileSystemItem> getChildren();

    void display();

    void displayRecursive(String indentation);

    // --------------- CRUD ---------------

    FileSystemItem getChild(String name);

    FileSystemItem add(FileSystemItem child);

    void remove(FileSystemItem child);

    void clearChildren();

    // --------------- Main Operations ---------------

    boolean isLeaf();

    void setLeaf(boolean leaf);

    void propagateChange(Consumer<FileSystemItem> change);

    FileSystemItem search(String name);
    FileSystemItem search(String name, int depthLimit);
}



