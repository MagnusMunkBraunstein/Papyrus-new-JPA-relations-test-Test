package org.example.papyrijpastructuretest.controller;

import org.example.papyrijpastructuretest.exception.userContext.UserNotAuthenticatedException;
import org.example.papyrijpastructuretest.service.FileSystemService;
import org.example.papyrijpastructuretest.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FileSystemController {
    private final UserContext userContext;
    private final FileSystemService fileSystemService;

    @Autowired
    public FileSystemController(UserContext userContext, FileSystemService fileSystemService) {
        this.userContext = userContext;
        this.fileSystemService = fileSystemService;
    }

    @PostMapping("/move")
    public ResponseEntity<?> moveItem(@RequestParam String sourcePath,
                                      @RequestParam String targetPath) {
        try {
            fileSystemService.moveItem(sourcePath, targetPath);
            return ResponseEntity.ok().build();
        } catch (UserNotAuthenticatedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}