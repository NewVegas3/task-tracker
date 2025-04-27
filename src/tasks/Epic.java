package tasks;

import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList<Integer> subtaskOfEpic = new ArrayList<>();

    public Epic(int id, String title, String description, String status) {
        super(id, title, description, status);
    }

    public ArrayList<Integer> getSubtaskOfEpic() {
        return subtaskOfEpic;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskOfEpic=" + subtaskOfEpic +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
