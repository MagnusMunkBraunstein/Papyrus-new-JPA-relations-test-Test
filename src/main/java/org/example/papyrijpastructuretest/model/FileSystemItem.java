package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    void display(String indent);

    // --------------- Setters ---------------

    void addChild(FileSystemItem child);

    void removeChild(FileSystemItem child);

    void clearChildren();

    // --------------- Main Operations ---------------

    boolean isLeaf();

    void setLeaf(boolean leaf);

    void propagateChange(Consumer<FileSystemItem> change);

    FileSystemItem search(String name);
    FileSystemItem search(String name, int depthLimit);
}



