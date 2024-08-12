package esoft.book.model;

//import org.junit.jupiter.api.Test;

import org.junit.Test;

import esoft.com.exception.InvalidValueException;
import esoft.com.exception.NullValueException;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.*;
//import esoft.book.model.Subject;


public class BookTest {

    @Test
    public void testValidBookCreation() {
    	Date birthdate = new Date(1);
        Author author = new Author("fname", "mname", "lname", birthdate, "bio");
        

        Book book = new Book(
            300, "Valid Title", new Date(), "Valid Publisher", Subject.ARTS,
            "teste", "Valid description", "thumbnail.com", 19.99, new Date(), "9123456789012", 400, Backing.HARDBACK,
            "tamanho", author
        );

        assertNotNull(book);
    }

    @Test
    public void testNullPubDate() {
    	Date birthdate = new Date(1);
        try {
            new Book(
                300, "Valid Title", null, "Valid Publisher", Subject.ARTS,
                null, "Valid description", "thumbnail.com", 19.99, new Date(), "9123456789012", 400, Backing.HARDBACK,
                "tamanho", new Author("fname", "mname", "lname", birthdate, "bio")
            );
            fail("Expected IllegalArgumentException for null pubDate");
        } catch (NullValueException e) {
            assertEquals("Data de publicação não pode ser nula. cannot be null", e.getMessage());
        }
    }

    @Test
    public void testInvalidISBN() {
    	Date birthdate = new Date(1);
        try {
            new Book(
                300, "Valid Title", new Date(), "Valid Publisher", Subject.ARTS,
                null, "Valid description", "thumbnail.com", 19.99, new Date(), "invalid_isbn", 400, Backing.HARDBACK,
                "tamanho", new Author("fname", "mname", "lname", birthdate, "bio")
            );
            fail("Expected IllegalArgumentException for invalid ISBN");
        } catch (InvalidValueException e) {
            assertTrue(e.getMessage().contains("invalid_isbn"));
        }
    }

    @Test
    public void testNegativePrice() {
    	Date birthdate = new Date(1);
        try {
            new Book(
                300, "Valid Title", new Date(), "Valid Publisher", Subject.ARTS,
                null, "Valid description", "thumbnail.com", -19.99, new Date(), "9123456789012", 400, Backing.HARDBACK,
                "tamanho", new Author("fname", "mname", "lname", birthdate, "bio")
            );
            fail("Expected IllegalArgumentException for negative price");
        } catch (IllegalArgumentException e) {
            assertEquals(" must be a positive number.", e.getMessage());
        }
    }

    @Test
    public void testTooLongPublisher() {
    	Date birthdate = new Date(1);
        try {
            new Book(
                300, "Valid Title", new Date(), "A".repeat(101), Subject.ARTS,
                "Publisher".repeat(120), "Valid description", "thumbnail.com", 19.99, new Date(), "9123456789012", 400, Backing.HARDBACK,
                "tamanho", new Author("fname", "mname", "lname", birthdate, "bio")
            );
            fail("Expected IllegalArgumentException for too long publisher");
        } catch (InvalidValueException e) {
        	System.out.println(e.getMessage());
        	assertTrue(e.getMessage().contains("100"));
        }
    }

    @Test
    public void testGettersAndSetters() {
        Date birthdate = new Date(1);
        Author author = new Author("fname", "mname", "lname", birthdate, "bio");

        Book book = new Book(
            400, "Valid Title", new Date(), "Valid Publisher", Subject.ARTS,
            "Valid description", "thumbnail.com", "image.com",
            19.99, new Date(), "9123456789012", 400, Backing.HARDBACK,
            "tamanho", author
        );

        // Test getters
        assertEquals("Valid Title", book.getTitle());
        assertEquals(new Date().getClass(), book.getPubDate().getClass());
        assertEquals("Valid Publisher", book.getPublisher());
        assertEquals("Valid description", book.getDesc());
        assertEquals("thumbnail.com", book.getThumbnail());
        assertEquals("image.com", book.getImage());
        assertEquals(19.99, book.getSrp(),0.1);
        assertEquals(new Date().getClass(), book.getAvail().getClass());
        assertEquals("9123456789012", book.getIsbn());
        assertEquals(400, book.getPage());
        assertEquals(Backing.HARDBACK, book.getBacking());
        assertEquals("tamanho", book.getDimensions());
        assertNotNull(book.getAuthor());
        assertEquals(Subject.ARTS, book.getSubject());
    }
}