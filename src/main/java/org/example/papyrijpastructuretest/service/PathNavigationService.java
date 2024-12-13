package org.example.papyrijpastructuretest.service;

import org.example.papyrijpastructuretest.utils.FileSystemUtils;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathNavigationService extends UtilService {



    public PathNavigationService(UserContext userContext, FileSystemUtils fileSystemUtils, MovementUtils movementUtils, ValidationUtils validationUtils, PathNavigationUtils pathUtils) {
        super(userContext, fileSystemUtils, movementUtils, validationUtils, pathUtils);
    }


    /* -----------------------------------------------------------------------------------------------
            Service class for navigating the file system for a given user.

                    - This class is responsible for resolving paths to file system items for a given user.

                    implemented
                    - combination of
                        -- user context
                        -- utils
                        -- concrete FileSystemItem instances
                    - MVC structure
                        -- for Repository access
                        -- service layer
                            -- for business logic
                            -- service operations

     -----------------------------------------------------------------------------------------------     */





}
