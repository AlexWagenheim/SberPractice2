import java.util.List;

public class Pet {
    private String name;
    private Status status;
    private List<Photo> photosList;

    public Pet(String name, Status status, List<Photo> photosList) {
        this.name = name;
        this.status = status;
        this.photosList = photosList;
    }
}
