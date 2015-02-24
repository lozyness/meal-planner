package shawley;

/**
 * Created by laura on 24/02/15.
 */
public class Account implements IParticipant{

    private String name;

    private Account(String name, String id) {
        this.name = name;
    }

    @Override
    public String id() {
        return "id";
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void editName(String newName) {
        this.name = newName;
    }


    public static Account createNewAccount(String name, String id) {
        return new Account(name, id);
    }
}
