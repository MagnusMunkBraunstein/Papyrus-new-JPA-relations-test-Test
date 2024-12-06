package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field_Resource {

    @EmbeddedId
    private Field_ResourceId id;

    @ManyToOne
    @MapsId("field")
    @JoinColumn(name = "field_id")
    private Field field;

    @ManyToOne
    @MapsId("resource")
    @JoinColumn(name = "resource_id")
    private Resource resource;
}