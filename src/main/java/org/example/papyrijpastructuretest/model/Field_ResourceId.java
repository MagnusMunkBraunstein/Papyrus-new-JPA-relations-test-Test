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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field_ResourceId that = (Field_ResourceId) o;
        return Objects.equals(field, that.field) && Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, resource);
    }

}
