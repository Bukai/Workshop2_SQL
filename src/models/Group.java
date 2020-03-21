package src.models;

public class Group {

    private int id;
    private String name;

    public Group() {

    }

    public Group(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "- id: "+ id + " - " + name + "\n";
    }
}
