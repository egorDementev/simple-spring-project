package org.example.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PetModel implements Serializable {
    private static final PetModel instance = new PetModel();
    private final Map<Integer, Pet> model;

    public PetModel() {
        model = new HashMap<Integer, Pet>();
    }
    public static PetModel getInstance() {
        return instance;
    }
    public void add(Pet pet, Integer id) {
        model.put(id, pet);
    }
    public Pet getFromList(Integer id) {
        return model.get(id);
    }
    public boolean isPetInModel(Integer id) {
        for (Map.Entry<Integer, Pet> entry: model.entrySet()) {
            if (Objects.equals(id, entry.getKey()))
                return true;
        }
        return false;
    }
    public void deletePet(Integer id) {
        model.remove(id);
    }
    public void updatePet(Integer id, String name, String type, int age) {
        model.replace(id, new Pet(name, type, age));
    }
    public Map<Integer, Pet> getAll() {
        return model;
    }
}
