package manager;

import tasks.Epic;
import tasks.SimpleTask;
import tasks.SubTask;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private int nextId = 1;

    private HashMap<Integer, SimpleTask> simpleTask = new HashMap<>();
    private HashMap<Integer, Epic> epicTask = new HashMap<>();
    private HashMap<Integer, SubTask> subTask = new HashMap<>();




    public ArrayList<SimpleTask> showSimpleTask() { //a. Получение списка всех простых задач.
        return new ArrayList<>(simpleTask.values());
    }

    public ArrayList<Epic> getEpicTask() { //Получение списка всех epic.
        return new ArrayList<>(epicTask.values());
    }

    public ArrayList<SubTask> showSubTask() { //Получение списка всех подзадач.
        return new ArrayList<>(subTask.values());
    }

    public SimpleTask getSimpleTask(int simpleId) { //c. Получение по идентификатору
        return simpleTask.get(simpleId);
    }

    public Epic getEpicTask(int epicId) {
        return epicTask.get(epicId);
    }

    public SubTask getSubTask(int subId) {
        return subTask.get(subId);
    }

    public ArrayList<SubTask> getSubTasksOfEpic(int epicId) {  //Получение списка всех подзадач определённого эпика.
        Epic epic = getEpicTask(epicId);
        ArrayList<SubTask> subTasksOfEpic = new ArrayList<>();
        for (Integer id : epic.getSubtaskOfEpic()) {
            subTasksOfEpic.add(getSubTask(id));
        }
        return subTasksOfEpic;
    }

    public void deleteAllSimpleTask() { //b. Удаление всех задач.
        simpleTask.clear();
    }

    public void deleteAllEpicTask() { //b. Удаление всех задач.
        epicTask.clear();
        subTask.clear();
    }

    public void deleteAllSubTask() {
        subTask.clear();
        for (Epic epic : epicTask.values()) {
            epic.getSubtaskOfEpic().clear();
            updateStatusEpic(epic);
        }
    }

    public void addSimpleTask(SimpleTask task) { //d. Создание. Сам объект должен передаваться в качестве параметра.
        task.setId(nextId);
        simpleTask.put(task.getId(), task);
        nextId++;
    }

    public void addEpicTask(Epic epic) { //d. Создание. Сам объект должен передаваться в качестве параметра.
        epic.setId(nextId);
        epicTask.put(epic.getId(), epic);
        nextId++;
    }

    public void addSubTask(SubTask task) { //d. Создание. Сам объект должен передаваться в качестве параметра.
        if (epicTask.containsKey(task.getEpicId())) {
            task.setId(nextId);
            subTask.put(task.getId(), task);
            nextId++;
            Epic epic = getEpicTask(task.getEpicId());
            epic.getSubtaskOfEpic().add(task.getId());
            updateStatusEpic(epic);
        }
    }

    public void updateStatusEpic(Epic epic) {
        int chekNew = 1;
        int chekDone = 1;

        for (Integer id : epic.getSubtaskOfEpic()) {
            if (!subTask.get(id).getStatus().equals("NEW")) {
                chekNew = 0;
            } else if (!subTask.get(id).getStatus().equals("DONE")) {
                chekDone = 0;
            }
        }
        if (chekNew == 1) {
            epic.setStatus("NEW");
        } else if (chekDone == 1) {
            epic.setStatus("DONE");
        } else {
            epic.setStatus("IN_PROGRESS");
        }
    }

    public void updateSimpleTask(SimpleTask task) {  //e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        simpleTask.put(task.getId(), task);
    }

    public void updateEpicTask(Epic epic) {  //e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        epicTask.put(epic.getId(), epic);
    }

    public void updateSubTask(SubTask task) { //e. Обновление. Новая версия объекта с верным идентификатором передаётся в виде параметра.
        subTask.put(task.getId(), task);
        Epic epic = getEpicTask(task.getEpicId());
        updateEpicTask(epic);
    }

    public void removeSimpleTusk(int id) { //f. Удаление по идентификатору.
        simpleTask.remove(id);
    }

    public void removeEpicTask(int epicId) { //f. Удаление по идентификатору.
        Epic epic = getEpicTask(epicId);
        for (Integer id : epic.getSubtaskOfEpic()) {
            subTask.remove(id);
        }
        epicTask.remove(epicId);
    }

    public void removeSubTask(int subId) { //f. Удаление по идентификатору.
        int epicId = subTask.get(subId).getEpicId();
        Epic epic = getEpicTask(epicId);
        epicTask.get(epicId).getSubtaskOfEpic().remove(Integer.valueOf(subId));
        updateStatusEpic(epic);
        subTask.remove(subId);
    }
}