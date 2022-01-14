package com.bddtest.bddtest.steps;

import com.bddtest.bddtest.UserClient;
import com.bddtest.bddtest.domain.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

public class UserSteps {

    private final UserClient userClient;

    private List<User> userList;

    public UserSteps(UserClient userClient) {
        this.userClient = userClient;
    }

    @Given("a user named {string} {string} in the system")
    public void aPersonNamedInTheSystem(String firstName, String lastName) {
        userClient.addPerson(new User(firstName, lastName));
    }

    @When("getting a list of users")
    public void gettingAListOfUsers() {
        userList = userClient.getAllUsers();
    }

    @Then("this list will contain")
    public void thisListWillContain(DataTable dataTable) {
        assertThat(userList).isEqualTo(convertToPersonList(dataTable));
    }

    private List<User> convertToPersonList(DataTable dataTable) {
        List<User> result = new ArrayList<>();
        for(List<String> userAsString: dataTable.<String>asLists(String.class)) {
            result.add(new User(userAsString.get(0), userAsString.get(1)));
        }
        return result;
    }
}
