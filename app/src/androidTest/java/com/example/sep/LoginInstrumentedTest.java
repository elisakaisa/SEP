package com.example.sep;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.sep.database.Employees;

import org.junit.Test;
import org.junit.runner.RunWith;


import org.junit.Rule;

import java.util.Arrays;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

    // make sure to be logged out before performing the tests on the emulator or phone

    @Rule
    public ActivityScenarioRule<ActivityLogin> activityRule = new ActivityScenarioRule<>(ActivityLogin.class);

    @Rule
    public IntentsTestRule<ActivityLogin> intentsTestRule =
            new IntentsTestRule<>(ActivityLogin.class);

    @Test
    public void loginSeniorCustomerService() {

        // Type text
        onView(withId(R.id.et_username)).perform(typeText("Janet"));
        onView(withId(R.id.et_password)).perform(typeText("password"));

        // Check that the text was changed.
        onView(withId(R.id.et_username)).check(matches(withText("Janet")));
        onView(withId(R.id.et_password)).check(matches(withText("password")));

        // press button
        onView(withId(R.id.btn_login)).perform(click());
        intended(allOf(
                hasComponent(BaseActivity.class.getName()),
                hasExtra("name", "Janet"),
                hasExtra("role", "Senior Customer Service Officer")));

        // correct view
        onView(withId(R.id.tv_title)).check(matches(withText("Event list")));
        onView(withId(R.id.fab_add)).check(matches(not(isDisplayed())));

        //logout
        onView(withId(R.id.img_btn_login)).perform(click());
    }

    @Test
    public void loginCustomerService() {

        List<String> names = Arrays.asList("Sarah", "Sam", "Judy", "Carine");

        for (int i = 0; i < names.size(); i++) {
            // Type text
            onView(withId(R.id.et_username)).perform(typeText(names.get(i)));
            onView(withId(R.id.et_password)).perform(typeText("password"));

            // Check that the text was changed.
            onView(withId(R.id.et_username)).check(matches(withText(names.get(i))));
            onView(withId(R.id.et_password)).check(matches(withText("password")));

            // press button
            onView(withId(R.id.btn_login)).perform(click());
            intended(allOf(
                    hasComponent(BaseActivity.class.getName()),
                    hasExtra("name", names.get(i)),
                    hasExtra("role", "Customer Service")));

            // correct view
            onView(withId(R.id.tv_title)).check(matches(withText("Event list")));
            onView(withId(R.id.fab_add)).check(matches(isDisplayed()));
            onView(withId(R.id.bottom_navigation_fim)).check(matches(not(isDisplayed())));
            onView(withId(R.id.bottom_navigation_fm)).check(matches(not(isDisplayed())));

            //logout
            onView(withId(R.id.img_btn_login)).perform(click());
        }
    }

    @Test
    public void loginFinancialManager() {

        // Type text
        onView(withId(R.id.et_username)).perform(typeText("Alice"));
        onView(withId(R.id.et_password)).perform(typeText("password"));

        // Check that the text was changed.
        onView(withId(R.id.et_username)).check(matches(withText("Alice")));
        onView(withId(R.id.et_password)).check(matches(withText("password")));

        // press button
        onView(withId(R.id.btn_login)).perform(click());
        intended(allOf(
                hasComponent(BaseActivity.class.getName()),
                hasExtra("name", "Alice"),
                hasExtra("role", "Financial manager")));

        // correct view
        onView(withId(R.id.tv_title)).check(matches(withText("Event list")));
        onView(withId(R.id.fab_add)).check(matches(not(isDisplayed())));
        onView(withId(R.id.bottom_navigation_fm)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_financial_requests)).perform(click());
        onView(withId(R.id.tv_title)).check(matches(withText("Financial requests list")));

        //logout
        onView(withId(R.id.img_btn_login)).perform(click());
    }

    @Test
    public void loginAdministrationDepartmentManager() {

        // Type text
        onView(withId(R.id.et_username)).perform(typeText("Mike"));
        onView(withId(R.id.et_password)).perform(typeText("password"));

        // Check that the text was changed.
        onView(withId(R.id.et_username)).check(matches(withText("Mike")));
        onView(withId(R.id.et_password)).check(matches(withText("password")));

        // press button
        onView(withId(R.id.btn_login)).perform(click());
        intended(allOf(
                hasComponent(BaseActivity.class.getName()),
                hasExtra("name", "Mike"),
                hasExtra("role", "Administration department manager")));

        // correct view
        onView(withId(R.id.tv_title)).check(matches(withText("Event list")));
        onView(withId(R.id.fab_add)).check(matches(not(isDisplayed())));

        //logout
        onView(withId(R.id.img_btn_login)).perform(click());
    }

    @Test
    public void loginProductionManager() {

        // Type text
        onView(withId(R.id.et_username)).perform(typeText("Jack"));
        onView(withId(R.id.et_password)).perform(typeText("password"));

        // Check that the text was changed.
        onView(withId(R.id.et_username)).check(matches(withText("Jack")));
        onView(withId(R.id.et_password)).check(matches(withText("password")));

        // press button
        onView(withId(R.id.btn_login)).perform(click());
        intended(allOf(
                hasComponent(BaseActivity.class.getName()),
                hasExtra("name", "Jack"),
                hasExtra("role", Employees.PRODUCTION_MANAGER)));

        // correct view
        onView(withId(R.id.tv_title)).check(matches(withText("Event list")));
        onView(withId(R.id.fab_add)).check(matches(not(isDisplayed())));
        onView(withId(R.id.bottom_navigation_fim)).check(matches(isDisplayed()));

        onView(withId(R.id.nav_fin_requests_fim)).perform(click());
        onView(withId(R.id.tv_title)).check(matches(withText("Financial requests list")));

        onView(withId(R.id.nav_res_requests_fim)).perform(click());
        onView(withId(R.id.tv_title)).check(matches(withText("Recruitment request list")));

        onView(withId(R.id.nav_tasks_fim)).perform(click());
        onView(withId(R.id.tv_title_task_list)).check(matches(withText("Task List")));

        //logout
        onView(withId(R.id.img_btn_login)).perform(click());
    }

    @Test
    public void loginSubTeams() {

        List<String> names = Arrays.asList(
                "Tobias", "Magdalena", "Antony", "Adam",
                "Julia", "Raymond", "Magy", "Angelina",
                "Christian", "Nicolas", "Michael", "Robert",
                "Helen", "Kate");

        for (int i = 0; i < names.size(); i++) {
            // Type text
            onView(withId(R.id.et_username)).perform(typeText(names.get(i)));
            onView(withId(R.id.et_password)).perform(typeText("password"));

            // Check that the text was changed.
            onView(withId(R.id.et_username)).check(matches(withText(names.get(i))));

            // press button
            onView(withId(R.id.btn_login)).perform(click());
            intended(allOf(
                    hasComponent(BaseActivity.class.getName()),
                    hasExtra("name", names.get(i))));

            // correct view
            onView(withId(R.id.tv_title_task_list)).check(matches(withText("Task List")));
            onView(withId(R.id.fab_add_task)).check(matches(not(isDisplayed())));
            onView(withId(R.id.bottom_navigation_fim)).check(matches(not(isDisplayed())));
            onView(withId(R.id.bottom_navigation_fm)).check(matches(not(isDisplayed())));

            //logout
            onView(withId(R.id.img_btn_login)).perform(click());
        }
    }

    @Test
    public void loginHRManager() {

        // Type text
        onView(withId(R.id.et_username)).perform(typeText("Simon"));
        onView(withId(R.id.et_password)).perform(typeText("password"));

        // Check that the text was changed.
        onView(withId(R.id.et_username)).check(matches(withText("Simon")));
        onView(withId(R.id.et_password)).check(matches(withText("password")));

        // press button
        onView(withId(R.id.btn_login)).perform(click());
        intended(allOf(
                hasComponent(BaseActivity.class.getName()),
                hasExtra("name", "Simon"),
                hasExtra("role", "Senior HR Manager")));

        // correct view
        onView(withId(R.id.tv_title)).check(matches(withText("Recruitment request list")));
        onView(withId(R.id.fab_add_request)).check(matches(not(isDisplayed())));

        //logout
        onView(withId(R.id.img_btn_login)).perform(click());
    }


    @Test
    public void loginWithWrongCredentials() {

        // Type text
        onView(withId(R.id.et_username)).perform(typeText("Janet"));
        onView(withId(R.id.et_password)).perform(typeText("jzdwwd"));

        // Check that the text was changed.
        onView(withId(R.id.et_username)).check(matches(withText("Janet")));
        onView(withId(R.id.et_password)).check(matches(withText("jzdwwd")));

        // press button
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.et_password)).check(matches(isDisplayed()));
    }

}