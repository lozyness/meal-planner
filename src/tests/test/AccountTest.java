package test;

import org.junit.Before;
import org.junit.Test;
import shawley.Account;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private Account account;

    @Before
    public void setup() {
        this.account = Account.createNewAccount("name");
    }

    @Test
    public void testAccountCreation() {
        assertEquals("Name not as expected","name", this.account.name());
        assertEquals("Unique Identifier not as expected","id", this.account.id());
    }

    @Test
    public void testAccountModificationNameChangeUpdatesNameReturned() {
        this.account.editName("newName");
        assertEquals("Name not as expected","newName", this.account.name());
    }

}