package org.example.papyrijpastructuretest.utils;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.FileSystemItem;
import org.example.papyrijpastructuretest.model.FileSystemItemImpl;
import org.springframework.stereotype.Component;

@Component
public class MovementUtils {


    public static void executeMove(FileSystemItem item, FileSystemItem newParent) {
        if (item == null || newParent == null || newParent.isLeaf()) {
            return;
        }
        FileSystemItemImpl itemImpl = (FileSystemItemImpl) item;

        Field oldParent = itemImpl.getParent();
            oldParent.remove(item);
            newParent.add(item);

        itemImpl.setParent((Field) newParent);
    }
}
