package com.villacis.kevin.cazarpatos

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for LoginActivity
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginScreenDisplayed() {
        // Verify that key UI elements are displayed
        onView(withId(R.id.logo_image)).check(matches(isDisplayed()))
        onView(withId(R.id.app_name_text)).check(matches(isDisplayed()))
        onView(withId(R.id.email_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.password_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.login_button)).check(matches(isDisplayed()))
        onView(withId(R.id.forgot_password_text)).check(matches(isDisplayed()))
        onView(withId(R.id.create_account_text)).check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyEmailShowsError() {
        // Leave email empty and try to login
        onView(withId(R.id.password_edit_text)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.login_button)).perform(click())
        
        // Verify error is shown for empty email
        onView(withId(R.id.email_input_layout)).check(matches(hasDescendant(withText("El correo electrónico es obligatorio"))))
    }

    @Test
    fun testEmptyPasswordShowsError() {
        // Enter email but leave password empty
        onView(withId(R.id.email_edit_text)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.login_button)).perform(click())
        
        // Verify error is shown for empty password
        onView(withId(R.id.password_input_layout)).check(matches(hasDescendant(withText("La contraseña es obligatoria"))))
    }

    @Test
    fun testInvalidEmailShowsError() {
        // Enter invalid email format
        onView(withId(R.id.email_edit_text)).perform(typeText("notanemail"), closeSoftKeyboard())
        onView(withId(R.id.password_edit_text)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.login_button)).perform(click())
        
        // Verify error is shown for invalid email
        onView(withId(R.id.email_input_layout)).check(matches(hasDescendant(withText("Ingresa un correo electrónico válido"))))
    }

    @Test
    fun testShortPasswordShowsError() {
        // Enter password that's too short
        onView(withId(R.id.email_edit_text)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.password_edit_text)).perform(typeText("12345"), closeSoftKeyboard())
        onView(withId(R.id.login_button)).perform(click())
        
        // Verify error is shown for short password
        onView(withId(R.id.password_input_layout)).check(matches(hasDescendant(withText("La contraseña debe tener al menos 6 caracteres"))))
    }

    @Test
    fun testValidCredentials() {
        // Enter valid credentials
        onView(withId(R.id.email_edit_text)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.password_edit_text)).perform(typeText("password123"), closeSoftKeyboard())
        onView(withId(R.id.login_button)).perform(click())
        
        // With invalid credentials, we should see error toast
        // Note: Testing toasts is complex in Espresso, so we just verify no crashes occur
    }

    @Test
    fun testForgotPasswordClick() {
        // Click on forgot password link
        onView(withId(R.id.forgot_password_text)).perform(click())
        
        // Verify it doesn't crash (toast will be shown)
    }

    @Test
    fun testCreateAccountClick() {
        // Click on create account link
        onView(withId(R.id.create_account_text)).perform(click())
        
        // Verify it doesn't crash (toast will be shown)
    }
}
