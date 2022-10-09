package com.example.sep.viewModel;

import static org.junit.Assert.*;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.sep.TestVariables;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class EventViewModelTest {

    private EventViewModel eventVM;


    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void init() {
        eventVM = new EventViewModel();
    }

    @Test
    public void getEvent() {
        assertNull(eventVM.getEvent().getValue());
    }

    @Test
    public void setEvent() {
    }

    @Test
    public void setIdentifier() {
        eventVM.setIdentifier(3);
        assertEquals(eventVM.identifier, 3);
    }

    @Test
    public void getIdentifier() {
        eventVM.setIdentifier(4);
        assertEquals(eventVM.getIdentifier(), 4);
    }
}