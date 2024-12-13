package org.example.papyrijpastructuretest.utils;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.FileSystemItem;
import org.example.papyrijpastructuretest.model.FileSystemItemImpl;
import org.example.papyrijpastructuretest.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PathNavigationUtils {

    public static String getPath(FileSystemItemImpl item) {
        List<String> pathParts = new ArrayList<>();
        FileSystemItem current = item;

        while (current != null) {
            pathParts.addFirst(current.getName());
            current = current.getParent();
        }

        return String.join("/", pathParts);
    }

    public static FileSystemItem findByPath(FileSystemItemImpl root, String path) {
        // Path resolution logic


        return new Field();
    }


}
