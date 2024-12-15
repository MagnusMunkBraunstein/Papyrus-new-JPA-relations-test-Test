// User.java
package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Field rootField;

    // to be continued
    public User () {
        rootField = new Field("root");
    }
}