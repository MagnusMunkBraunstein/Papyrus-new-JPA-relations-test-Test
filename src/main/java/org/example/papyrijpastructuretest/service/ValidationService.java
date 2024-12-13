package org.example.papyrijpastructuretest.service;

import org.example.papyrijpastructuretest.utils.FileSystemUtils;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidationService extends UtilService{

    @Autowired
    public ValidationService(UserContext userContext, FileSystemUtils fileSystemUtils, MovementUtils movementUtils, ValidationUtils validationUtils, PathNavigationUtils pathNavigationUtils) {
        super(userContext, fileSystemUtils, movementUtils, validationUtils, pathNavigationUtils);
    }
}
