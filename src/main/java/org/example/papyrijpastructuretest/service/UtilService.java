package org.example.papyrijpastructuretest.service;

import org.example.papyrijpastructuretest.utils.FileSystemUtils;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class UtilService {
    protected final UserContext userContext;
    protected final FileSystemUtils fileSystemUtils;
    protected final MovementUtils movementUtils;
    protected final ValidationUtils validationUtils;
    protected final PathNavigationUtils pathUtils;

    @Autowired
    public UtilService(UserContext userContext, FileSystemUtils fileSystemUtils, MovementUtils movementUtils, ValidationUtils validationUtils, PathNavigationUtils pathUtils) {
        this.userContext = userContext;
        this.fileSystemUtils = fileSystemUtils;
        this.movementUtils = movementUtils;
        this.validationUtils = validationUtils;
        this.pathUtils = pathUtils;
    }

}
