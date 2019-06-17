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
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //when mood log button is pushed
    @Test
    public void mainActivityTest() {
        ViewInteraction verticalViewPager = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        verticalViewPager.perform(swipeRight());

        ViewInteraction verticalViewPager2 = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        verticalViewPager2.perform(swipeRight());

        ViewInteraction verticalViewPager3 = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        verticalViewPager3.perform(swipeLeft());

        ViewInteraction verticalViewPager4 = onView(
                allOf(withId(R.id.viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        verticalViewPager4.perform(swipeRight());

        //when mood log button is pressed
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.mood_log),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        allOf(withId(android.R.id.custom),
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("test"), closeSoftKeyboard());
    }

    //when mood history button is pushed
    @Test
    public void mainActivityTest7() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.mood_chart),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
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


//    @Test
//    public void testSetAlarm() throws Exception {
//        AlarmReceiver receiver = new AlarmReceiver();
//        receiver.setAlarm(context);
//        assertThat(isAlarmSet()).isTrue();
//    }


//    @Test
//    public void getNextScheduledAlarm_shouldReturnRepeatingAlarms() {
//        assertThat(AlarmManager.getNextScheduledAlarm()).isNull();
//        long now = new Date().getTime();
//        Intent intent = new Intent(activity, activity.getClass());
//        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0);
//        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, now, INTERVAL_HOUR, pendingIntent);
//        AlarmManager.ScheduledAlarm scheduledAlarm = shadowAlarmManager.getNextScheduledAlarm();
//        assertThat(AlarmManager.getNextScheduledAlarm()).isNull();
//        assertRepeatingScheduledAlarm(now, INTERVAL_HOUR, pendingIntent, scheduledAlarm);
//    }


//    @Test
//    public void set_shouldRegisterAlarm() {
//        assertThat(shadowAlarmManager.getNextScheduledAlarm()).isNull();
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME, 0,
//                PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()), 0));
//        assertThat(shadowAlarmManager.getNextScheduledAlarm()).isNotNull();
//    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {

    }

    @Test
    public void onNewIntent() {
    }
}
