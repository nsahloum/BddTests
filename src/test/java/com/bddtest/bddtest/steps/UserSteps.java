package com.bddtest.bddtest.steps;

import com.bddtest.bddtest.UserClient;
import com.bddtest.bddtest.domain.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps {

    private final UserClient userClient;
    private User addedUser;
    private List<User> userList;

    @Autowired
    public UserSteps(UserClient userClient) {
        this.userClient = userClient;
    }

    @Given("a user named {string} {string} in the system")
    public void addUser(String firstName, String lastName) {
        addedUser = userClient.addUser(new User(firstName,lastName));
    }

    @When("getting a list of users")
    public void getUsers() {
        userList = userClient.getUsers();
    }

    @Then("this list will contain")
    public void assertUserInList(DataTable dataTable){
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
