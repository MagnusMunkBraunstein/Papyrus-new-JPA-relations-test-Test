package org.example.papyrijpastructuretest.utils;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.FileSystemItem;
import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PathNavigationUtils {

    // operations
    public static Field getRoot(FileSystemItem item) {
        FileSystemItem current = item;
        while (current.getParent() != null) {
            current = current.getParent();
        }
        return (Field) current;
    }

    public static String getPath(FileSystemItem item) {
        if (item == null || item.isLeaf()) {
            return null;
        }

        List<String> pathParts = new ArrayList<>();
        FileSystemItem current = item;

        while (current != null) {
            pathParts.addFirst(current.getName());
            current = current.getParent();
        }

        return String.join("/", pathParts);
    }

    public static FileSystemItem findByPath(Field root, String path) {
        if (root == null || root.isLeaf()) {
            return null;
        }

        FileSystemItem current = root;
        String[] pathParts = path.split("/");

        for (String part : pathParts) {
            if (root.getChildren() == null) {
                return null;
            }
            boolean found = false;
            for (FileSystemItem child : root.getChildren()) {
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
