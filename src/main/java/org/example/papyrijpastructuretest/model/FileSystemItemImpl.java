package org.example.papyrijpastructuretest.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class FileSystemItemImpl extends FileSystemItem {
    // Core abstract methods that Field/Resource must implement
    abstract public List<FileSystemItem> getChildren();

    // Core properties all implementations need
    private String name;
    private Field parent;


    /* --------------- Implemented Operations ---------------
         > FileSystemItem
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




    /* --------------- Static Domain Classes --------------- */

    public static class PathNavigator {
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

    public static class Validator {
        public static boolean validateHierarchy(FileSystemItemImpl item) {
            if (item.isRoot()) return true;
            return !item.getChildren().isEmpty() && hasUniqueName(item);
        }

        public static boolean hasUniqueName(FileSystemItemImpl item) {
            if (item.getParent() == null) return true;

            return item.getParent().getChildren().stream()
                    .filter(sibling -> sibling != item)
                    .noneMatch(sibling -> sibling.getName().equals(item.getName()));
        }

        public static boolean isRoot(FileSystemItemImpl item) {
            return item.getParent() == null && "root".equals(item.getName());
        }
    }

    public static class Mover {
        public static void move(FileSystemItemImpl item, Field newParent) {
            if (Validator.isRoot(item)) {
                throw new IllegalStateException("Cannot move root");
            }

            if (!Validator.hasUniqueName(item)) {
                throw new IllegalStateException("Name conflict in target location");
            }

            Field oldParent = item.getParent();
            if (oldParent != null) {
                oldParent.remove(item);
            }

            newParent.add(item);
            item.setParent(newParent);
        }
    }

    /* --------------- Instance Methods --------------- */

    // These delegate to the static classes
    public void move(Field newParent) {
        Mover.move(this, newParent);
    }

    public String getPath() {
        return PathNavigator.getPath(this);
    }

    public boolean validate() {
        return Validator.validateHierarchy(this);
    }

    public boolean isRoot() {
        return Validator.isRoot(this);
    }

    public boolean hasUniqueName() {
        return Validator.hasUniqueName(this);
    }
}