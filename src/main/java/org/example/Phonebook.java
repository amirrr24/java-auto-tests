package org.example;

import java.util.*;

public class Phonebook {
    private Map<String, List<String>> phonebook = new HashMap<>();

    public void add(String surname, String phone) {
        if (!phonebook.containsKey(surname)) {
            phonebook.put(surname, new ArrayList<>());
        }
        phonebook.get(surname).add(phone);
    }

    public List<String> get(String surname) {
        return phonebook.getOrDefault(surname, new ArrayList<>());
    }

    public void printAll() {
        for (Map.Entry<String, List<String>> entry : phonebook.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}