package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class Task {
    private String name;
    @Autowired
    @Qualifier("subTaskList")
    private List<SubTask> subTaskList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    public void executeSubTask() {
        System.out.println("Executing [TASK] " + name);
        if (subTaskList != null) {
            for (SubTask task : subTaskList) {
                task.getSubTaskMessage();
            }
        }
    }
}
