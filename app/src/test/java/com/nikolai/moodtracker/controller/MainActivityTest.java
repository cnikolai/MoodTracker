package com.nikolai.moodtracker.controller;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.nikolai.moodtracker.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction verticalViewPager = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(android.R.id.content),
                                        0),
                                0),
                        ViewMatchers.isDisplayed()));
        verticalViewPager.perform(ViewActions.swipeLeft());

        ViewInteraction appCompatImageButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.mood_log), ViewMatchers.withContentDescription("Mood log icon"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withClassName(Matchers.is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        ViewMatchers.isDisplayed()));
        appCompatImageButton.perform(ViewActions.click());

        ViewInteraction editText = Espresso.onView(
                Matchers.allOf(childAtPosition(
                        Matchers.allOf(ViewMatchers.withId(android.R.id.custom),
                                childAtPosition(
                                        ViewMatchers.withClassName(Matchers.is("android.widget.FrameLayout")),
                                        0)),
                        0),
                        ViewMatchers.isDisplayed()));
        editText.perform(ViewActions.replaceText("I'm very happy today because it is not raining!"), ViewActions.closeSoftKeyboard());

        ViewInteraction appCompatButton = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(android.R.id.button1), ViewMatchers.withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withClassName(Matchers.is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(ViewActions.scrollTo(), ViewActions.click());

        ViewInteraction appCompatImageButton2 = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.mood_chart), ViewMatchers.withContentDescription("Mood chart icon"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withClassName(Matchers.is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatImageButton2.perform(ViewActions.click());

        ViewInteraction appCompatImageView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.today_mood_note), ViewMatchers.withContentDescription("Today Mood Note"),
                        childAtPosition(
                                Matchers.allOf(ViewMatchers.withId(R.id.today),
                                        childAtPosition(
                                                ViewMatchers.withId(R.id.mood_chart_parent),
                                                1)),
                                1),
                        ViewMatchers.isDisplayed()));
        appCompatImageView.perform(ViewActions.click());

        ViewInteraction actionMenuItemView = Espresso.onView(
                Matchers.allOf(ViewMatchers.withId(R.id.action_mood_chart), ViewMatchers.withContentDescription("Mood Chart"),
                        childAtPosition(
                                childAtPosition(
                                        ViewMatchers.withId(R.id.my_toolbar),
                                        1),
                                0),
                        ViewMatchers.isDisplayed()));
        actionMenuItemView.perform(ViewActions.click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
