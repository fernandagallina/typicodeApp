package com.example.fernanda.typicodeapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.fernanda.typicodeapp.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by fernanda on 21/11/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserFragmentTest {

    @Rule
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {

    }

    @Test
    public void checkIfUserInfoIsDisplayed() throws InterruptedException {
        Thread.sleep(2000, 0);
        Espresso.onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        Thread.sleep(2000, 0);
        Espresso.onView(withId(R.id.name_title)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.username)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.email)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.website)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.phone)).check(matches(isDisplayed()));
    }
}
