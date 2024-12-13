package org.example.papyrijpastructuretest.utils;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.FileSystemItemImpl;
import org.springframework.stereotype.Component;

@Component
public class MovementUtils {


    public static void executeMove(FileSystemItemImpl fileSystemItem, Field newParent) {
        Field oldParent = fileSystemItem.getParent();
        oldParent.remove(fileSystemItem);
        newParent.add(fileSystemItem);
        fileSystemItem.setParent(newParent);
    }
}
