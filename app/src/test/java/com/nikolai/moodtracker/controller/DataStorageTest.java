package com.nikolai.moodtracker.controller;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataStorageTest {
    private DataStorage dataStorage;
    @Mock
    SharedPreferences mPreferences;
    @Mock
    SharedPreferences.Editor preferencesEditor;

    //@Mock
    //MyDatabase databaseMock;

//    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void Before() {
        MockitoAnnotations.initMocks(this);
        //mPreferences = mock(SharedPreferences.class);
        dataStorage = new DataStorage(mPreferences);
        when(mPreferences.edit()).thenReturn(preferencesEditor);
    }

    @Test
    public void storeData() {
        //arrange
        //act
        dataStorage.storeIntData("Mon",2);
        //assert
        verify(preferencesEditor,never()).putInt("Tue",-3);
        verify(preferencesEditor).putInt("Mon",2);
        verify(preferencesEditor).apply();
    }

    @Test
    public void mockitoExample() {
        //mock a class
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    //@Test
//    public void testQuery()  {
//        ClassToTest t  = new ClassToTest(databaseMock);
//
//        boolean check = t.query("* from t");
//
//        assertTrue(check);
//
//        verify(databaseMock).query("* from t");
//
//    }

    //@Test
//    public void test1()  {
//        //  create mock
//        MyClass test = mock(MyClass.class);
//
//        // define return value for method getUniqueId()
//        when(test.getUniqueId()).thenReturn(43);
//
//        // use mock in test....
//        assertEquals(test.getUniqueId(), 43);
//    }


}