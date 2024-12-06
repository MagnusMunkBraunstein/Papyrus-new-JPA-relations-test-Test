package org.example.papyrijpastructuretest.service;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.repositories.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    // Method to print the hierarchy of fields entities starting from a given field
    public Field getFieldById(Long id) {
        // Use the fieldRepository to find the field by Id, return null if not found
        return fieldRepository.findById(id).orElse(null);
    }

    // Method to print the hierarchy of fields entities starting from a given field
    public void printFieldHierarchy(Field field, String indent) {
        // Print the name of the field
        System.out.println(indent + field.getName());
        // Retrieve the list of children fields for the given field
        List<Field> children = fieldRepository.findByParentField(field);
        // Recursively call the method for each child field with increased indentation
        for (Field child : children) {
            // Recursively print the hierarchy of the child field
            printFieldHierarchy(child, indent + "  ");
        }
    }
}
