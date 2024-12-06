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

    public Field getFieldById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }

    public void printFieldHierarchy(Field field, String indent) {
        System.out.println(indent + field.getName());
        List<Field> children = fieldRepository.findByParentField(field);
        for (Field child : children) {
            printFieldHierarchy(child, indent + "  ");
        }
    }
}
