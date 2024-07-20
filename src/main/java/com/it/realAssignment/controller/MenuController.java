package com.it.realAssignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.realAssignment.model.Menu;
import com.it.realAssignment.service.MenuService;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/modify")
    public ResponseEntity<Menu> modifyMenu(@RequestBody List<String> inputs) {
        Menu modifiedMenu = menuService.modifyMenu(inputs);
        return ResponseEntity.ok(modifiedMenu);
    }
}
