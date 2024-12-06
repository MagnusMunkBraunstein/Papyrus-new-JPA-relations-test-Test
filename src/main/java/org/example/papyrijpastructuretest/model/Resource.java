package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends FileSystem{


    @ManyToOne
    private Field field;



}
