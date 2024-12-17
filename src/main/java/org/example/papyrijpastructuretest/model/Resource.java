package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "resources")
public class Resource extends FileSystemItemImpl {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnore // avoid infinite recursion
    @ToString.Exclude
    private Field parent;


    private String author;
    private String description;
    private String type;
    private String url;
    private String refId;


    // --------------- Constructors ---------------

    public Resource(String name) {
        super();
        setName(name);
    }


    public List<FileSystemItem> getChildren() {
        return Collections.emptyList();
    }

    // ------------------ Setters ------------------

    public Resource setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Resource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Resource setType(String type) {
        this.type = type;
        return this;
    }

    public Resource setUrl(String url) {
        this.url = url;
        return this;
    }

    public Resource setRefId(String refId) {
        this.refId = refId;
        return this;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
