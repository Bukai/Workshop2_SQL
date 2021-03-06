package src.models;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int user_id;

    public Solution() {

    }

    public Solution(int id, String description, String updated){
        this.id = id;
        this.description = description;
        this.updated = updated;
    }


    public Solution(String created, String description, int exercise_id, int user_id) {
        this.created = created;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUsers_id() {
        return user_id;
    }

    public void setUsers_id(int users_id) {
        this.user_id = users_id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", created='" + created + '\'' + ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", users_id=" + user_id + "\n";
    }
}
