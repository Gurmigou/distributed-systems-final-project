package com.example.distributedsystemsrest.controller;

import com.example.distributedsystemsrest.model.Group;
import com.example.distributedsystemsrest.repository.DataAccessObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/group")
@RestController
public class GroupController {
    private final DataAccessObject dataAccessObject = new DataAccessObject();

    @GetMapping
    public ResponseEntity<Group> getGroupById(Integer id) {
        Group group = dataAccessObject
                .findGroupById(id)
                .orElseThrow(() -> new IllegalStateException("Group not found"));
        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(Group group) {
        dataAccessObject.createNewGroup(group);
        return ResponseEntity.ok(group);
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(Group group) {
        dataAccessObject.updateGroup(group);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping
    public ResponseEntity<Group> deleteGroup(Integer id) {
        dataAccessObject.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
