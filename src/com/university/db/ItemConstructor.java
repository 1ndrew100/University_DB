package com.university.db;

public interface ItemConstructor<Item extends DBItem> {
    Item construct(String[] rawValues);
}
