package org.example.papyrijpastructuretest.utils;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.model.FileSystemItem;
import org.example.papyrijpastructuretest.model.FileSystemItemImpl;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {
    public static boolean isValidMove(FileSystemItem sourcePrm, FileSystemItem targetPrm) {
        FileSystemItemImpl source = validateType(sourcePrm, null);
        FileSystemItemImpl target = validateType(null, targetPrm);
        
        if (source.isRoot()) return false;
        if (source.equals(target)) return false;
        if (target == null) return false;
        if (source.getParent().equals(target)) return false;
        if (target.isRoot()) return false;
        if (target.getChildren().stream().anyMatch(child
                -> child.getName().equals(source.getName()))) return false;
        return true;
    }



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




    // --------------- Private Methods ---------------


    private static FileSystemItemImpl validateType(FileSystemItem sourcePrm, FileSystemItem targetPrm) {
        FileSystemItemImpl source = validateAbstractType(sourcePrm);
        Field target = validateFieldType(targetPrm);

        if (source != null) return source;
        if (target != null) return target;

        return null;
    }

    private static FileSystemItemImpl validateAbstractType(FileSystemItem sourcePrm) {

        if (sourcePrm instanceof FileSystemItemImpl) {
            return (FileSystemItemImpl) sourcePrm;
        }

        return null;
    }

    private static Field validateFieldType(FileSystemItem targetPrm) {

        if(targetPrm instanceof Field) {
            return (Field) targetPrm;
        }

        return null;
    }
}
