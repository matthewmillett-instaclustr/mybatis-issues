package test;

public enum Status {
    QUEUED("queued task", "queued"),
    IN_PROGRESS("in progress task", "in progress"),
    COMPLETED("completed task", "completed");


    public final String name;
    public final String displayName;

    Status(final String name, final String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return name;
    }
}
