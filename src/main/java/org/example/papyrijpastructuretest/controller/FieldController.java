package org.example.papyrijpastructuretest.controller;

import org.example.papyrijpastructuretest.model.Field;
import org.example.papyrijpastructuretest.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping("/fields/{id}/hierarchy")
    public void showFieldHierarchy(@PathVariable Long id) {
        Field field = fieldService.getFieldById(id);
        fieldService.printFieldHierarchy(field, "");
    }

    @GetMapping("/fields")
    public Field getFieldHierarchy(@PathVariable Long id) {
        return fieldService.getFieldById(id);
    }

}
