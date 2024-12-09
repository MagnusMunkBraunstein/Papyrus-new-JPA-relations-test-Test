// User.java
package org.example.papyrijpastructuretest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

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
    private Field field;


    // to be continued
    public User () {
        field = new Field("root");
    }
}