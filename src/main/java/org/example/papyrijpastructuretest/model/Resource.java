package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends FileSystemItemImpl {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // parent_id is the column in the child table
    @JsonIgnore
    @ToString.Exclude // avoid infinite recursion
    protected Field parent;

    // --------------- Children ---------------
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Field> childrenFields = new ArrayList<>();

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Resource> childrenResources = new ArrayList<>();



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


}
