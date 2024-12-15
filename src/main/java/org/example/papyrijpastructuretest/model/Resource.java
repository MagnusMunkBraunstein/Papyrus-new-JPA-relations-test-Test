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

public class Resource extends FileSystemItemImpl {


    private String name;
    private String author;
    private String description;
    private String type;
    private String url;
    private String refId;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "savedResources")
    private List<User> users;

    @ManyToOne
    private Field field;

    // --------------- Constructors ---------------

    public Resource(String name) {
        super();
        setName(name);
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

    public Resource setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Resource setField(Field field) {
        this.field = field;
        return this;
    }


}
