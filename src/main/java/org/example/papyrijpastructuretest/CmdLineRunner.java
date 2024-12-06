package org.example.papyrijpastructuretest;

import org.example.papyrijpastructuretest.model.*;
import org.example.papyrijpastructuretest.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CmdLineRunner {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, FieldRepository fieldRepository, ResourceRepository resourceRepository, FieldResourceRepository fieldResourceRepository) {
        return args -> {
            // Create a hardcoded user
            User user = new User(null, "hardcodedUser", "user@example.com");
            userRepository.save(user);

            // Create and save parent fields first
            Field rootField = new Field(null, user, "Root Field", null);
            fieldRepository.save(rootField);

            // Create and save child fields
            Field child1 = new Field(null, user, "Child 1", rootField);
            Field child2 = new Field(null, user, "Child 2", rootField);
            Field child3 = new Field(null, user, "Child 3", rootField);
            Field child4 = new Field(null, user, "Child 4", rootField);
            fieldRepository.save(child1);
            fieldRepository.save(child2);
            fieldRepository.save(child3);
            fieldRepository.save(child4);

            // Create and save sub-child fields
            Field subChild1 = new Field(null, user, "Sub Child 1", child1);
            Field subChild2 = new Field(null, user, "Sub Child 2", child1);
            Field subChild3 = new Field(null, user, "Sub Child 3", child2);
            Field subChild4 = new Field(null, user, "Sub Child 4", child4);
            fieldRepository.save(subChild1);
            fieldRepository.save(subChild2);
            fieldRepository.save(subChild3);
            fieldRepository.save(subChild4);

            // Create some resources
            Resource resource1 = new Resource(null, "title1", "author1");
            Resource resource2 = new Resource(null, "title2", "author2");

            // Save the resources
            resourceRepository.save(resource1);
            resourceRepository.save(resource2);

            // Create and save Field_Resource relationships
            Field_Resource fieldResource1 = new Field_Resource(new Field_ResourceId(rootField.getId(), resource1.getId()), rootField, resource1);
            Field_Resource fieldResource2 = new Field_Resource(new Field_ResourceId(child1.getId(), resource2.getId()), child1, resource2);

            fieldResourceRepository.save(fieldResource1);
            fieldResourceRepository.save(fieldResource2);
        };
    }
}