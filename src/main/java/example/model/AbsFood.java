package example.model;

public abstract class AbsFood {

    private String name() {
        return "AbsFood";
    }

    public String getOriginName() {
        return name();
    }
}
