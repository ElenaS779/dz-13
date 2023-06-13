package testDB;

import models.Person;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static utils.DBReader.*;

public class TestDB {
    private Person person;

    @Test
    public void allPersonsFromDB() {
        List<Person> persons = getAllPersonsFromDB();
        Assert.assertFalse(persons.isEmpty());
    }
    @Test
    public void PersonsFromDB() {
        Person person = selectById(3);
        Assert.assertNotNull(person);
    }
    @Test
    public void insertPersonToDB(){
        insertToDB(11,"Bob","Bukkly",33);
    }
    @Test
    public void updatePersonAgeToDB() {
        updateDB(4,20);
    }
    @Test
    public void deletePerson(){
        deleteById(9);
    }
}