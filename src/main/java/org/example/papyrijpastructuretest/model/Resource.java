package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends FileSystemItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field parent;

    public Resource(String name) {
        super();
        setName(name);
    }

    @Override
    public List<FileSystemItem> getChildren() {
        return Collections.emptyList(); // Leaf node has no children
    }

}
