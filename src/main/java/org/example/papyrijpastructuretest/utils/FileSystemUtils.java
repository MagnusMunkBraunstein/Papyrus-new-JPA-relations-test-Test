package org.example.papyrijpastructuretest.utils;

import org.example.papyrijpastructuretest.model.FileSystemItem;
import org.example.papyrijpastructuretest.model.FileSystemItemImpl;
import org.example.papyrijpastructuretest.model.User;
import org.springframework.stereotype.Component;

@Component
public class FileSystemUtils {


    // update root with change
    /* ---------------------------------
    change = any utils operation that changes FileSystemItems of root
        - fx. moveItem, createItem, deleteItem

     */

    // search
    public static FileSystemItem search(FileSystemItem item, String name) {

        if (item.getName().equals(name)) {
            return item;
        }

        // Recursively search in children
        for (FileSystemItem child : item.getChildren()) {
            FileSystemItem result = child.search(name); // recursive call
            if (result != null) {
                return result; // Return as soon as a match is found
            }
        }

        return null;
    }

    public static FileSystemItem search(FileSystemItem item, String name, int depthLimit) {
        if (depthLimit < 0) {
            return null;
        }
        if (item.getName().equals(name)) {
            return item;
        }
        for (FileSystemItem child : item.getChildren()) {
            FileSystemItem result = child.search(name, depthLimit - 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }



}
