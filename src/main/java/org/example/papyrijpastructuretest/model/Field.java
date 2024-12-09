package org.example.papyrijpastructuretest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Field extends FileSystemItemImpl {

    // --------------- User ---------------
    @OneToOne(mappedBy = "field")
    @JoinColumn(name = "user_id", nullable = true)
    @ToString.Exclude
    private User user;

    // --------------- Parent ---------------
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Field parent;

    // --------------- Children ---------------
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Resource> childrenResources;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Field> childrenFields = new ArrayList<>();


    // --------------- Constructors ---------------

    public Field(String name) {
        super(name);
    }


    // --------------- CRUD ---------------
    public FileSystemItem add(FileSystemItem item) {
        System.out.println("DEBUG add");
        System.out.println(" - item = " + item);

        item.setParent(this);

        if (item instanceof Resource) {
            childrenResources.add((Resource) item);
        } else if (item instanceof Field) {
            childrenFields.add((Field) item);
        }

        return this;
    }

    public void update(FileSystemItem child) {
        if (child instanceof Resource) {
            for (Resource resource : childrenResources) {
                if (resource.getName().equals(child.getName())) {
                    resource.update((Resource) child);
                }
            }
        } else if (child instanceof Field) {
            for (Field field : childrenFields) {
                if (field.getName().equals(child.getName())) {
                    field.update((Field) child);
                }
            }
        }
    }

    @Override
    void update(Resource child) {

    }

    @Override
    void update(Field child) {

    }


    public void remove(FileSystemItem item) {
        if (item instanceof Resource) {
            childrenResources.remove(item);
        } else if (item instanceof Field) {
            childrenFields.remove(item);
        }
    }
    // --------------- Methods ---------------

    public FileSystemItem get(String childName) {
        System.out.println("DEBUG get");
        System.out.println(" children: "+ childrenResources+","+childrenFields);
        System.out.println(" - childName = " + childName);
        Field field = getField(childName);
        System.out.println(" - field = " + field);
        return getField(childName) != null
                ? getField(childName)
                : getResource(childName);
    }

    public Resource getResource(String childName) {
        System.out.println("DEBUG getResource");
        System.out.println(" - childName = " + childName);
        for (Resource resource : childrenResources) {
            System.out.println(" - resource.getName() = " + resource.getName());
            if (resource.getName().equals(childName)) {
                return resource;
            }
        }
        return null;
    }
    public Field getField(String childName) {
        System.out.println("DEBUG getField");
        System.out.println(" - childName = " + childName);
        for (Field field : childrenFields) {
            System.out.println(" - field.getName() = " + field.getName());
            if (field.getName().equals(childName)) {
                return field;
            }
        }
        return null;
    }

    public void set(FileSystemItem item, String path) {
        String[] pathParts = path.split("/");

        if (pathParts.length == 1) { // last part of the path
            if (item instanceof Field) {
                childrenFields.add((Field) item);
            } else if (item instanceof Resource) {
                childrenResources.add((Resource) item);
            }
        } else { // not the last part of the path
            String childName = pathParts[0];
            FileSystemItem child = get(childName);
            if (child instanceof Field) {
                ((Field) child).set(item, path.substring(childName.length() + 1));
            }
        }

    }


    // --------------- ToString ---------------

    @Override
    public String toString() {
        return "Field{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", parent=" + (parent != null ? parent.getId() : "null") +
                ", user id=" + (user != null ? user.getId() : "null") +
                '}';
    }


}