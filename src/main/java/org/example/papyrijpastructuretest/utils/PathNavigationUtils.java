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

    // operations
    public static FileSystemItem getRoot(FileSystemItem item) {
        FileSystemItem current = item;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return current;
    }

    public static String getPath(FileSystemItemImpl item) {
        List<String> pathParts = new ArrayList<>();
        FileSystemItem current = item;

        while (current != null) {
            pathParts.addFirst(current.getName());
            current = current.getParent();
        }

        return String.join("/", pathParts);
    }

    public static FileSystemItem findByPath(FileSystemItemImpl item, String path) {
        FileSystemItem current = item;
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


}
