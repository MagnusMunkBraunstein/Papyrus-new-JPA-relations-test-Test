package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Field_ResourceId implements Serializable {
    private Long field;
    private Long resource;

    // Getters, Setters, hashCode, equals
    public Long getField() {
        return field;
    }

    public void setField(Long field) {
        this.field = field;
    }

    public Long getResource() {
        return resource;
    }

    public void setResource(Long resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        // Check if the current object is being compared with itself
        if (this == o) return true;
        // Check if the current object is being compared is null or if the classes are different
        if (o == null || getClass() != o.getClass()) return false;
        // Cast the object to Field_ResourceId for comparison
        Field_ResourceId that = (Field_ResourceId) o;
        // Compare the field and resource values
        return Objects.equals(field, that.field) && Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, resource);
    }

}
