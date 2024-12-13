package org.example.papyrijpastructuretest.service;

import lombok.NoArgsConstructor;
import org.example.papyrijpastructuretest.utils.FileSystemUtils;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class MovementService extends UtilService{
    @Autowired
    public MovementService(FileSystemUtils fileSystemUtils, MovementUtils movementUtils, ValidationUtils validationUtils, PathNavigationUtils pathNavigationUtils) {
        super(fileSystemUtils, movementUtils, validationUtils, pathNavigationUtils);
    }

}
