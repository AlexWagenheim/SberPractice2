package sber.practice.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtils {

    public static Animal mapToProductDto(Pet pet) throws NoSuchFieldException, IllegalAccessException {
        Class<Pet> petClass = Pet.class;

        Field nameField = petClass.getDeclaredField("name");
        nameField.setAccessible(true);
        String title = (String) nameField.get(pet);

        Field statusField = petClass.getDeclaredField("status");
        statusField.setAccessible(true);
        boolean isAvailable = statusField.get(pet).equals(Status.AVAILABLE);
        boolean isSold = statusField.get(pet).equals(Status.SOLD);

        Field photoListField = petClass.getDeclaredField("photosList");
        photoListField.setAccessible(true);
        Map<String, String> photosMap = new HashMap<>();
        for (Photo photo: (List<Photo>) photoListField.get(pet)) {
            photosMap.put(photo.getName(), photo.getURL());
        }

        Animal animal = new Animal();
        Class<Animal> animalClass = Animal.class;

        Field titleField = animalClass.getDeclaredField("title");
        titleField.setAccessible(true);
        titleField.set(animal, title);

        Field isAvailableField = animalClass.getDeclaredField("isAvailable");
        isAvailableField.setAccessible(true);
        isAvailableField.set(animal, isAvailable);

        Field isSoldField = animalClass.getDeclaredField("isSold");
        isSoldField.setAccessible(true);
        isSoldField.set(animal, isSold);

        Field photosMapField = animalClass.getDeclaredField("photosMap");
        photosMapField.setAccessible(true);
        photosMapField.set(animal, photosMap);

        return animal;
    }
}
