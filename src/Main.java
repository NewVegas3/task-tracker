import manager.Manager;
import tasks.Epic;
import tasks.SimpleTask;
import tasks.SubTask;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SimpleTask simpleTask = new SimpleTask(0, "Переезд", "111","NEW");
        SimpleTask simpleTask1 = new SimpleTask(0, "Переезд1", "111","NEW");

        Epic epic = new Epic(0, "dataClass.Epic", "222", "NEW");
        SubTask subtask = new SubTask(0, "Subtask1", "333", "NEW", 3);
        SubTask subtask1 = new SubTask(0, "Subtask2", "333", "NEW", 3);

        Epic epic1 = new Epic(0, "Epic1", "222", "NEW");
        SubTask subtask3 = new SubTask(0, "Subtask3", "333", "NEW", 6);

        Manager manager = new Manager();


        manager.addSimpleTask(simpleTask);
        manager.addSimpleTask(simpleTask1);

        manager.addEpicTask(epic);
        manager.addSubTask(subtask);
        manager.addSubTask(subtask1);

        manager.addEpicTask(epic1);
        manager.addSubTask(subtask3);

        System.out.println(manager.showSimpleTask());
        System.out.println(manager.getEpicTask());
        System.out.println(manager.showSubTask());



    }
}