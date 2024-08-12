package esoft.model;

import esoft.com.exception.EmptyValueException;
import esoft.com.exception.InvalidValueException;
import esoft.com.exception.NullValueException;
import esoft.book.model.Author;
import esoft.com.util.FileUtil;
import java.lang.reflect.Field;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.Test;
import soft.art.util.LogTrace;
import static org.junit.Assert.*;

public class AuthorTest {
    

    @Test
    public void testAuthorConstructorAndGetters() {
        String fname = "John";
        String mname = "F.";
        String lname = "Doe";
        Date birthdate = new Date(1);
        String bio = "John F. Doe is a fictional character.";

        Author author = new Author(fname, mname, lname, birthdate, bio);
        assertEquals(fname, author.getFname());
        assertEquals(mname, author.getMname());
        assertEquals(lname, author.getLname());
        assertEquals(birthdate, author.getBirthdate());
        assertEquals(bio, author.getBio());
    }

    @Test
    public void testAuthorFields() throws NoSuchFieldException {
        Field fieldFname = Author.class.getDeclaredField("fname");
        assertFalse(fieldFname.isAccessible());
        Field fieldMname = Author.class.getDeclaredField("mname");
        assertFalse(fieldMname.isAccessible());
        Field fieldLname = Author.class.getDeclaredField("lname");
        assertFalse(fieldLname.isAccessible());
        Field fieldBirthdate = Author.class.getDeclaredField("birthdate");
        assertFalse(fieldBirthdate.isAccessible());
        Field fieldBio = Author.class.getDeclaredField("bio");
        assertFalse(fieldBio.isAccessible());
    }

    @Test
    public void testAuthorCreationWithValidParameters() {
        Date birthdate = new Date();
        Author author = new Author("John", "Michael", "Doe", birthdate, "Author bio");
        // If no exception is thrown, the test will pass
    }

    @Test
    public void testAuthorCreationWithNullFirstName() {
        Date birthdate = new Date();
        try {
            new Author(null, "Michael", "Doe", birthdate, "Author bio");
            fail("Expected NullValueException to be thrown");
        } catch (NullValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected NullValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithEmptyFirstName() {
        Date birthdate = new Date();
        try {
            new Author("", "Michael", "Doe", birthdate, "Author bio");
            fail("Expected EmptyValueException to be thrown");
        } catch (EmptyValueException e) {
            // Expected exception
       } catch (Exception e) {
            fail("Expected EmptyValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithInvalidFirstName() {
        Date birthdate = new Date();
        try {
            new Author("John!", "Michael", "Doe", birthdate, "Author bio");
            fail("Expected InvalidValueException to be thrown");
        } catch (InvalidValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected InvalidValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithLongFirstName() {
        Date birthdate = new Date();
        String longName = "JJJJJJJJJJ"
                + "JJJJJJJJJJ"
                + "JJJJJJJJJJ"
                + "JJJJJJJJJJ"
                + "JJJJJJJJJJ" + "J";
        try {
            new Author(longName, "Michael", "Doe", birthdate, "Author bio");
            fail("Expected InvalidValueException to be thrown");
        } catch (InvalidValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected InvalidValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithNullMiddleName() {
        Date birthdate = new Date();
        try {
            new Author("John", null, "Doe", birthdate, "Author bio");
            fail("Expected NullValueException to be thrown");
        } catch (NullValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected NullValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithEmptyMiddleName() {
        Date birthdate = new Date();
        try {
            new Author("John", "", "Doe", birthdate, "Author bio");
            fail("Expected EmptyValueException to be thrown");
        } catch (EmptyValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected EmptyValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithNullLastName() {
        Date birthdate = new Date();
        try {
            new Author("John", "Michael", null, birthdate, "Author bio");
            fail("Expected NullValueException to be thrown");
        } catch (NullValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected NullValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithEmptyLastName() {
        Date birthdate = new Date();
        try {
            new Author("John", "Michael", "", birthdate, "Author bio");
            fail("Expected EmptyValueException to be thrown");
        } catch (EmptyValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected EmptyValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithNullBirthdate() {
        try {
            new Author("John", "Michael", "Doe", null, "Author bio");
            fail("Expected NullValueException to be thrown");
        } catch (NullValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected NullValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithNullBio() {
        Date birthdate = new Date();
        try {
            new Author("John", "Michael", "Doe", birthdate, null);
            fail("Expected NullValueException to be thrown");
        } catch (NullValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected NullValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithEmptyBio() {
        Date birthdate = new Date();
        try {
            new Author("John", "Michael", "Doe", birthdate, "");
            fail("Expected EmptyValueException to be thrown");
        } catch (EmptyValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected EmptyValueException, but got: " + e.getMessage());
        }
    }

    @Test
    public void testAuthorCreationWithInvalidBio() {
        Date birthdate = new Date();
        try {
            new Author("John", "Michael", "Doe", birthdate, "B");
            fail("Expected InvalidValueException to be thrown");
        } catch (InvalidValueException e) {
            // Expected exception
        } catch (Exception e) {
            fail("Expected InvalidValueException, but got: " + e.getMessage());
        }
    }
}
