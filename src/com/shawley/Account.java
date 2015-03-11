package shawley;

public class Account implements IParticipant{

    private String name;

    private Account(String name) {
        this.name = name;
    }

    @SuppressWarnings("SameReturnValue")
    public String id() {
        return "id";
    }

    public String name() {
        return this.name;
    }

    public void editName(@SuppressWarnings("SameParameterValue") String newName) {
        this.name = newName;
    }


    public static Account createNewAccount(@SuppressWarnings("SameParameterValue") String name) {
        return new Account(name);
    }
}
