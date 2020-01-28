package com.university.db;

import java.io.IOException;
import java.util.ArrayList;

public class DataBase {
    private ArrayList<ItemsManager> managers;

    public DataBase() {
        managers = new ArrayList<>();
    }

    public void loadAllCSV() throws IOException {
        for (ItemsManager itemsManager : managers)
            itemsManager.loadCSV();
    }

    public void saveAll() throws IOException {
        for (ItemsManager itemsManager : managers)
            itemsManager.saveCSV();
    }

    protected <Item extends DBItem> ItemsManager<Item> newItemsManager(ItemConstructor<Item> constructor) {
        ItemsManager<Item> newItemsManager = new ItemsManager<>(constructor);
        managers.add(newItemsManager);
        return newItemsManager;
    }
}
