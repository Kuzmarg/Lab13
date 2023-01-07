package task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Group<T> extends Task<T> {
    public String groupUuid;
    private List<Task<T>> tasks;

    @Override
    public String getId() {
        return super.getId();
    }

    public Group<T> addTask(Task<T> task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        groupUuid = UUID.randomUUID().toString();
        for (Task<T> task: tasks) {
            task.freeze();
        }
    }

    @Override
    void stamp(Visitor<T> visitor) {
        this.setHeader("groupID",visitor.onGroupStart(this).get("groupID"));
        for (Task<T> task: tasks) {
            visitor.onGroupStart(this);
            task.stamp(visitor);
        }
        visitor.onGroupEnd();
    }

    @Override
    public void apply(T arg) {
        this.freeze();
        tasks = Collections.unmodifiableList(tasks);
        this.stamp(new VisitorImpl<>());
        for (Task<T> task: tasks) {
            task.apply(arg);
        }
    }
}
