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

import android.content.Context;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.junit.Rule;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<ActivityLogin> activityRule = new ActivityScenarioRule<>(ActivityLogin.class);

    @Rule
    public IntentsTestRule<ActivityLogin> intentsTestRule =
            new IntentsTestRule<>(ActivityLogin.class);

    @Test
    public void loginWithCorrectCredentials() {

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