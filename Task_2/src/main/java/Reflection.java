import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reflection {

    private static Animal mapToProductDto(Pet cat) throws NoSuchFieldException, IllegalAccessException {
        Class<Pet> petClass = Pet.class;

        Field nameField = petClass.getDeclaredField("name");
        nameField.setAccessible(true);
        String title = (String) nameField.get(cat);

        Field statusField = petClass.getDeclaredField("status");
        statusField.setAccessible(true);
        boolean isAvailable = statusField.get(cat).equals(Status.AVAILABLE);
        boolean isSold = statusField.get(cat).equals(Status.SOLD);

        Field photoListField = petClass.getDeclaredField("photosList");
        photoListField.setAccessible(true);
        Map<String, String> photosMap = new HashMap<>();
        for (Photo photo: (List<Photo>) photoListField.get(cat)) {
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

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Pet cat = new Pet("Барсик", Status.AVAILABLE,
                List.of(new Photo("Барсик с цветочком",
                        "https://placekitten.com/200/300"),
                        new Photo("Барсик на ручках",
                        "https://placekitten.com/500/605")
                ));

        Animal animalCat = mapToProductDto(cat);
        System.out.println(animalCat);
    }
}
