package org.example.papyrijpastructuretest.service;

import org.example.papyrijpastructuretest.model.FileSystemItem;
import org.example.papyrijpastructuretest.model.User;
import org.example.papyrijpastructuretest.utils.FileSystemUtils;
import org.example.papyrijpastructuretest.utils.MovementUtils;
import org.example.papyrijpastructuretest.utils.PathNavigationUtils;
import org.example.papyrijpastructuretest.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class FileSystemService extends UtilService{

    @Autowired
    public FileSystemService(UserContext userContext, FileSystemUtils fileSystemUtils, MovementUtils movementUtils, ValidationUtils validationUtils, PathNavigationUtils pathNavigationUtils) {
        super(userContext, fileSystemUtils, movementUtils, validationUtils, pathNavigationUtils);
    }


    // Operations now have user context
    public void moveItem(String sourcePath, String targetPath) {
        User currentUser = userContext.getCurrentUser();

        FileSystemItem source = PathNavigationUtils.findByPath(currentUser.getRootField(), sourcePath);
        FileSystemItem target = PathNavigationUtils.findByPath(currentUser.getRootField(), targetPath);

        if (ValidationUtils.isValidMove(source, target)) {
            MovementUtils.executeMove(source, target);
        }
    }


}
