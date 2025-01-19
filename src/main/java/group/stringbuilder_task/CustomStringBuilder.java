package group.stringbuilder_task;

import java.util.Stack;

public class CustomStringBuilder {

    private static class StringBuilderSnapshot {
        private final String snapshot;

        public StringBuilderSnapshot(String snapshot) {
            this.snapshot = snapshot;
        }

        public String getState() {return snapshot;}
    }

    private static class StringBuilderSnapshotHistory {
        private final Stack<StringBuilderSnapshot> snapshots;

        public StringBuilderSnapshotHistory() {
            snapshots = new Stack<>();
        }

        public void addSnapshot(StringBuilderSnapshot snapshot) {
            snapshots.push(snapshot);
        }

        public StringBuilderSnapshot getSnapshot(){
            return snapshots.peek();
        }

        public StringBuilderSnapshot popSnapshot() {
            return snapshots.pop();
        }
    }

    private long snapshotHistorySize = 0;
    private StringBuilder stringBuilder = new StringBuilder();
    private final StringBuilderSnapshotHistory snapshotHistory = new StringBuilderSnapshotHistory();

    public void append(String str) {
        stringBuilder.append(str);
        saveSnapshot();
    }

    public void insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        saveSnapshot();
    }

    public void delete(int start, int end) {
        stringBuilder.delete(start, end);
        saveSnapshot();
    }

    public String toString() {
        return stringBuilder.toString();
    }

    public void undo() {
        if (snapshotHistorySize > 1){
            snapshotHistory.popSnapshot();
            snapshotHistorySize--;
            StringBuilderSnapshot snapshot = snapshotHistory.getSnapshot();
            stringBuilder = new StringBuilder(snapshot.getState());
        } else if (snapshotHistorySize == 1) {
            snapshotHistorySize--;
            StringBuilderSnapshot snapshot = snapshotHistory.getSnapshot();
            stringBuilder = new StringBuilder();
            snapshotHistory.popSnapshot();
        } else {
            System.out.println("No stringbuilder history to undo!");
        }
    }

    private void saveSnapshot() {
        StringBuilderSnapshot snapshot = new StringBuilderSnapshot(stringBuilder.toString());
        snapshotHistory.addSnapshot(snapshot);
        snapshotHistorySize++;
    }
}

