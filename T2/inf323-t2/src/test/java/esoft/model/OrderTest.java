package esoft.model;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import esoft.abs.model.Item;
import esoft.abs.model.Product;
import esoft.com.model.Address;
import esoft.com.model.Country;
import esoft.com.model.ShipType;
import esoft.order.model.CCTransaction;
import esoft.order.model.Cart;
import esoft.order.model.CreditCard;
import esoft.order.model.Customer;
import esoft.order.model.Order;
import esoft.order.model.OrderLine;
import esoft.order.model.OrderState;

public class OrderTest {

    private Order order;

    @Test
    public void shouldCreateOrderSuccessfully() {
        Item item = getValidItem();
        Country country = getValidCountry();
        Address address = getValidBillingAddress(country);
        Customer customer = getValidCustomer(address);
        Cart cart = getValidCart();
        CCTransaction transaction = getValidTransaction(country);
        Product product = getValidProduct(item);
        cart.changeLine(product, 3, 2.0);

        order = new Order(1, customer, new Date(1), cart, "Product", ShipType.UPS, new Date(1), OrderState.DELIVERED, address, address, transaction);

        assertEquals(1, order.getId());
        assertEquals(OrderState.DELIVERED, order.getStatus());
        assertEquals(ShipType.UPS, order.getShipType());
        assertEquals("jdoe@example.com", order.getCustomer().getEmail());
        assertEquals("Anytown", order.getBillingAddress().getCity());
        assertEquals(CreditCard.VISA, order.getCc().getType());
        assertEquals("USA", order.getShippingAddress().getCountry().getName());
        assertEquals(15.0, order.getSubTotal(), 0.0);
        assertEquals(18.23, order.getTotal(), 0.01);
        assertEquals(1, order.getLines().size());
        assertEquals(1.23, order.getTax(), 0.01);
        assertEquals(new Date(1), order.getDate());
        assertEquals(new Date(1), order.getShipDate());
    }

    @Test
    public void shouldEnsureOrderLineListIsImmutable() {
        Item item = getValidItem();
        Country country = getValidCountry();
        Address address = getValidBillingAddress(country);
        Customer customer = getValidCustomer(address);
        Cart cart = getValidCart();
        CCTransaction transaction = getValidTransaction(country);
        Product product = getValidProduct(item);
        cart.changeLine(product, 3, 2.0);

        order = new Order(1, customer, new Date(1), cart, "Product", ShipType.UPS, new Date(1), OrderState.DELIVERED, address, address, transaction);

        assertThrows(UnsupportedOperationException.class, () -> order.getLines().add(new OrderLine(product, 1, 5.0, "additional comment")));
    }

    @Test
    public void shouldThrowExceptionWhenOrderLineListIsNullOrEmpty() {
        try {
            Country country = getValidCountry();
            Address address = getValidBillingAddress(country);
            Customer customer = getValidCustomer(address);
            Cart cart = getValidCart();
            CCTransaction transaction = getValidTransaction(country);
            
            order = new Order(1, customer, new Date(1), cart, "Product", ShipType.UPS, new Date(1), OrderState.DELIVERED, address, address, transaction);
            
            fail("Expected IllegalArgumentException for empty or null order line list.");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void shouldThrowExceptionForNegativeTax() {
        try {
            Item item = getValidItem();
            Country country = getValidCountry();
            Address address = getValidBillingAddress(country);
            Customer customer = getCustomerWithourBalanceAndDiscount(address);
            Cart cart = getValidCart();
            Product product = getInvalidProduct(item);
            cart.changeLine(product, 3, 2.0);
            CCTransaction transaction = getValidTransaction(country);
            
            order = new Order(1, customer, new Date(1), cart, "Product", ShipType.UPS, new Date(1), OrderState.DELIVERED, address, address, transaction);
         
            fail("Expected IllegalArgumentException for negative tax value.");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void shouldUpdateOrderStatusSuccessfully() {
        Item item = getValidItem();
        Country country = getValidCountry();
        Address address = getValidBillingAddress(country);
        Customer customer = getValidCustomer(address);
        Cart cart = getValidCart();
        CCTransaction transaction = getValidTransaction(country);
        Product product = getValidProduct(item);
        
        cart.changeLine(product, 3, 2.0);
        order = new Order(1, customer, new Date(1), cart, "Product", ShipType.UPS, new Date(1), OrderState.DELIVERED, address, address, transaction);
        order.setStatus(OrderState.SHIPPED);
        
        assertEquals(OrderState.SHIPPED, order.getStatus());
    }

    @Test
    public void testOrderToString() {
    	String orderToString = "Order{id=1, customerName=jdoe}";
    	Item item = getValidItem();
    	Country country = getValidCountry();
    	Address address = getValidBillingAddress(country);
    	Customer customer = getValidCustomer(address);
    	Cart cart = getValidCart();
    	CCTransaction transaction = getValidTransaction(country);
    	Product item1 = getValidProduct(item);
    	
    	cart.changeLine(item1, 3, 2.0);
    	order = new Order(1, customer, new Date(1), cart, "Produto", ShipType.UPS, new Date(1), OrderState.DELIVERED, address, address, transaction);
    	assertEquals(orderToString, order.toString());
    }

    @Test
    public void shouldHaveDefaultConstructor() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Item item = getValidItem();
        Country country = getValidCountry();
        Address address = getValidBillingAddress(country);
        Customer customer = getValidCustomer(address);
        CCTransaction transaction = getValidTransaction(country);
        Product product = getValidProduct(item);

        Constructor<?>[] constructors = Order.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getModifiers() == 0) {
                constructor.setAccessible(true);
                List<OrderLine> orderLines = new ArrayList<>();
                orderLines.add(new OrderLine(product, 1, 0.0, "comment"));
                Order testOrder = (Order) constructor.newInstance(1, customer, new Date(1), 0.0, 0.0, orderLines, "something", ShipType.MAIL, new Date(0), OrderState.NEW, address, address, transaction);
                assertNotNull(testOrder);
            }
        }
    }

    private Customer getValidCustomer(Address address) {
    	return new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com", 
    			new Date(1), new Date(1), null, null, 0.0, 0.0, 0.0, new Date(1), "data", address);
    }
    
    private Customer getCustomerWithourBalanceAndDiscount(Address address) {
    	return new Customer(1, "jdoe", "password", "John", "Doe", "555-1234", "jdoe@example.com", 
    			new Date(1), new Date(1), null, null, -101, -101, 101, new Date(1), "data", address);
    }

    private Cart getValidCart() {
    	return new Cart(1, new Date(1));
    }
    
    private CCTransaction getValidTransaction(Country country) {
    	return new CCTransaction(CreditCard.VISA, 1234567890123456L, "John Doe", new Date(1), "12345", 100.0, new Date(1), country);
    }
    
    private Address getValidBillingAddress(Country country) {
    	return new Address(1, "123 Main St", "", "Anytown", "CA", "12345", country, "", "", "");
    }
    
    private Country getValidCountry() {
    	return new Country(1, "USA", "DOL", 1.0);
    }
    
    private Item getValidItem() {
    	return new Item(1, "Smartphone");
    }
    
    private Product getValidProduct(Item item) {
        return new Product() {
            @Override
            public int getId() { return 0; }
            @Override
            public double getCost() { return 5.0; }
            @Override
            public Item getItem() { return item; }
            @Override
            public int getQuantityOnHand() { return 10; }
            @Override
            public int getIdStock() { return 0; }
        };
    }

    private Product getInvalidProduct(Item item) {
        return new Product() {
            @Override
            public int getId() { return 0; }
            @Override
            public double getCost() { return -995.9; }
            @Override
            public Item getItem() { return item; }
            @Override
            public int getQuantityOnHand() { return -1; }
            @Override
            public int getIdStock() { return 0; }
        };
    }
}
