package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field extends FileSystem {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "field", cascade = CascadeType.ALL)
    private List<Resource> resources = new ArrayList<>();


    private List<Field> parrent;


}
