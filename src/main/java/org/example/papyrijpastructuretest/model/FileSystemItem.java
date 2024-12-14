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

    default FileSystemItem getRoot() {
        FileSystemItem current = this;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current;
    }

    default String getPath() {
        FileSystemItem current = this;
        StringBuilder path = new StringBuilder();
        while (current.getParent() != null) {
            path.insert(0, current.getName() + "/");
            current = current.getParent();
        }
        return path.toString();
    }

    default FileSystemItem findByPath(String path) {
        FileSystemItem current = this;
        String[] pathParts = path.split("/");
        for (String part : pathParts) {
            if (current.getChildren() == null) {
                return null;
            }
            boolean found = false;
            for (FileSystemItem child : current.getChildren()) {
                if (child.getName().equals(part)) {
                    current = child;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return null;
            }
        }
        return current;
    }

    default void validateHierarchy() {
        Queue<FileSystemItem> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            FileSystemItem current = queue.poll();
            if (current.getChildren() != null) {
                for (FileSystemItem child : current.getChildren()) {
                    if (child.getParent() != current) {
                        throw new IllegalStateException("Invalid hierarchy");
                    }
                    queue.add(child);
                }
            }
        }
    }

    default boolean hasUniqueName() {
        FileSystemItem current = this;
        String name = current.getName();
        FileSystemItem parent = current.getParent();
        if (parent == null) {
            return true;
        }
        for (FileSystemItem sibling : parent.getChildren()) {
            if (sibling != current && sibling.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    default boolean isRoot() {
        return getParent() == null && "root".equals(getName());
    }

    default void move(FileSystemItem newParent) {
        if (isRoot()) {
            return;
        }
        if (newParent == null) {
            return;
        }
        if (this.equals(newParent)) {
            return;
        }
        if (getParent().equals(newParent)) {
            return;
        }
        if (newParent.isRoot()) {
            return;
        }
        if (newParent.getChildren().stream().anyMatch(child -> child.getName().equals(getName()))) {
            return;
        }
        getParent().getChildren().remove(this);
        newParent.getChildren().add(this);
        setParent(newParent);
    }

    void display(String indent);

    // --------------- Setters ---------------

    void setParent(FileSystemItem parent);

    void setName(String name);

    void setChildren(List<FileSystemItem> children);

    void addChild(FileSystemItem child);

    void removeChild(FileSystemItem child);

    void forEachChild(Consumer<FileSystemItem> action);

    void clearChildren();

    void setRoot(boolean root);

    boolean isLeaf();

    void setLeaf(boolean leaf);

    void setPath(String path);

    String getFullPath();

    void setFullPath(String fullPath);

    void propagateChange(Consumer<FileSystemItem> change);

    FileSystemItem search(String name);
    FileSystemItem search(String name, int depthLimit);
}



