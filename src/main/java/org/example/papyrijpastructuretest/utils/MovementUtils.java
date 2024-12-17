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

        itemImpl.getParent()
                .remove(itemImpl);

        itemImpl.setParent((Field) newParent);
    }
}
