package com.it.realAssignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.realAssignment.entity.MenuEntity;
import com.it.realAssignment.model.Menu;
import com.it.realAssignment.model.Menu.MenuItem;
import com.it.realAssignment.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu modifyMenu(List<String> inputs) {
        Menu menu = getPredefinedMenu();

        for (String input : inputs) {
            String[] parts = input.split(":::");
            String target = parts[0];
            String replacement = parts[1];

            menu.getPopup().getMenuitem().forEach(item -> {
                if (item.getValue().equals(target)) {
                    item.setValue(replacement);
                }
            });
        }

        saveMenu(menu);
        return menu;
    }

    private Menu getPredefinedMenu() {
        Menu menu = new Menu();
        menu.setId("file");
        menu.setValue("File");

        Menu.Popup popup = new Menu.Popup();
        MenuItem newItem = new MenuItem();
        newItem.setValue("New");
        newItem.setOnclick("CreateDoc()");

        MenuItem openItem = new MenuItem();
        openItem.setValue("Open");
        openItem.setOnclick("OpenDoc()");

        MenuItem saveItem = new MenuItem();
        saveItem.setValue("Save");
        saveItem.setOnclick("SaveDoc()");

        popup.setMenuitem(List.of(newItem, openItem, saveItem));
        menu.setPopup(popup);

        return menu;
    }

    private void saveMenu(Menu menu) {
        MenuEntity entity = new MenuEntity();
        entity.setJsonmodel(convertMenuToJson(menu));
        menuRepository.save(entity);
    }

    private String convertMenuToJson(Menu menu) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(menu);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting menu to JSON", e);
        }
    }
}
