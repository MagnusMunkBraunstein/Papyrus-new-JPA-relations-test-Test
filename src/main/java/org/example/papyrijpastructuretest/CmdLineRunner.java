package org.example.papyrijpastructuretest;

import org.example.papyrijpastructuretest.model.*;
import org.example.papyrijpastructuretest.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class CmdLineRunner {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, FieldRepository fieldRepository, ResourceRepository resourceRepository) {
        return args -> {
            // Create a hardcoded user
            User user = new User();
                user.setName("User");
                user.setPassword(123);

            User savedUser = userRepository.save(user);

            System.out.println("User: " + savedUser.getField());
            System.out.println("Saved User: " + savedUser.getField());

            // get the root field
            Field rootField = fieldRepository.findById(savedUser.getField().getId()).get();

            // Create and Add child fields
            Field child1 = new Field("Child 1");
            Field child11 = new Field("Child 1.1");
            Field child3 = new Field("Child 3");
            Field child4 = new Field("Child 4");

            rootField.add(child1);
            rootField.display();
            rootField.get(child1.getName()).add(child11);
            rootField.add(child3);
            rootField.add(child4);


            // Create and save sub-child fields
            Field subChild1 = new Field("Sub Child 1");
            Field subChild2 = new Field("Sub Child 2");
            Field subChild3 = new Field("Sub Child 3");
            Field subChild4 = new Field("Sub Child 4");

            child1.add(subChild1);
            child1.add(subChild2);
            child1.add(subChild3);
            child1.add(subChild4);

            // Create some resources
            Resource resource1 = new Resource("resource1");
            Resource resource2 = new Resource("resource2");

            // Save the resources
            rootField.add(resource1)
                    .get("child1").add(resource2);
            rootField.update(child1);

            // Save the root field
            savedUser.setField(rootField);
            userRepository.save(savedUser);

            // find and call display()
            Optional<User> foundUser = userRepository.findById(savedUser.getId());
            if (foundUser.isPresent()) {
                User user1 = foundUser.get();
                System.out.println("User: " + user1.getName());
                user1.getField().display();
            }

        };
    }
}