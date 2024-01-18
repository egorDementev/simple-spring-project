package org.example.controller;

import org.example.logic.Pet;
import org.example.logic.PetModel;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1);
    @PostMapping(value = "/createPet", consumes = "application/json", produces = "application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        return "Вы успешно добавли животное по имени " + pet.getName() +
                " типа " + pet.getType() +
                " и возраста " + pet.getAge() + "!!!";
    }
    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }
    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }
    @DeleteMapping(value = "/deletePet", consumes = "application/json", produces = "application/json")
    public String deletePet(@RequestBody Map<String, Integer> id) {
        if (!petModel.isPetInModel(id.get("id")))
            return "Животного с ID " + id.get("id") + " нет в нашей базе, введите другой номер!!!";
        petModel.deletePet((id.get("id")));
        return "Животное успешно удалено!";
    }
    @PutMapping(value = "/updatePet", consumes = "application/json", produces = "application/json")
    public String updatePet(@RequestBody Map<String, String> pet) {
        if (!petModel.isPetInModel(Integer.parseInt(pet.get("id"))))
            return "Животного с ID " + pet.get("id") + " нет в нашей базе, введите другой номер!!!";
        petModel.updatePet(Integer.parseInt(pet.get("id")),
                pet.get("name"), pet.get("type"), Integer.parseInt(pet.get("age")));

        return "Животное успешно обновлено!";
    }
}
