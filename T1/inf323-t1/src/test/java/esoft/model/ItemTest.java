
package esoft.model;

import esoft.abs.model.Item;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esoft
 */
public class ItemTest {
    
    public ItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void testEqualsAndHashCode() {
        Item item1 = new ItemImpl();
        Item item2 = new ItemImpl();
        
        // Test equals
        assertTrue(item1.equals(item2));
        
        // Test hashCode
        assertEquals(item1.hashCode(), item2.hashCode());
    }
    
     @Test
    public void testToString() {
        Item item1 = new ItemImpl();
        assertEquals(item1.toString(), "Item{id=0, title=aaaa}");
    }
    
    public class ItemImpl extends Item {

        public ItemImpl() {
            super(0, "aaaa");
        }
    }
    
}
