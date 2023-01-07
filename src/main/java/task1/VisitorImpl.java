package task1;

import java.util.Map;

public class VisitorImpl<T> implements Visitor<T>{
    private String groupID;

    public void setGroupID(String groupID) {
        this.groupID = groupID;
        System.out.println("here " + groupID);
    }

    @Override
    public Map<String, String> onSignature(Task<T> task) {
        return Map.of("groups", groupID);
    }

    @Override
    public Map<String, String> onGroupStart(Task<T> task) {
        this.setGroupID(task.getId());
        return Map.of("groupID", groupID);
    }

    @Override
    public void onGroupEnd() {
        groupID = null;
    }
}
