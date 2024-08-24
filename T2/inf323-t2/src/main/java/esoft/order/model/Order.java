package esoft.order.model;


import esoft.abs.model.Context;
import esoft.abs.model.ListQtyLine;
import esoft.com.model.Address;

import java.util.ArrayList;
import esoft.order.model.Customer;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import esoft.abs.model.State;
import esoft.com.model.Address;
import esoft.com.model.ShipType;

/**
 * A classe Order representa uma ordem de compra, que é criada a partir de um carrinho.
 * Ela é imutável, exceto pelo atributo 'status', que pode ser alterado.
 */
public class Order implements ListQtyLine<OrderLine>, Context<OrderState>{

	private final double subTotal;	
	private final Date shipDate;	
	private final ShipType shipType;
	private final CCTransaction cc;	
    private final int id;
    private final Customer customer;
    private final Date date;
    private final Address billingAddress;
    private final Address shippingAddress;
    private final List<OrderLine> lines;
    private final double tax;
    private final double total;
    private OrderState status;
    private static final long serialVersionUID = -4194553399937996531L;
    
     /**
     * Construtor da classe Order.
     *
     * @param subTotal
     * @param shipDate
     * @param shipType
     * @param id                Identificador da ordem.
     * @param customer          Cliente associado à ordem.
     * @param date              Data da ordem.
     * @param billingAddress    Endereço de faturamento.
     * @param shippingAddress   Endereço de entrega.
     * @param lines             Linhas da ordem.
     * @param tax               Valor do imposto da ordem.
     * @param total             Valor total da ordem.
     * @param status            Estado atual da ordem.
     * @param tax 
     * @param total 
     */
    public Order(int id, Customer customer, Date date, Cart cart, String comment, ShipType shipType, 
			Date shipDate, OrderState status, Address billingAddress, Address shippingAddress, CCTransaction cc) {
    	
    	this.subTotal = cart.subTotal(customer.getDiscount());
		this.tax = cart.tax(customer.getDiscount());
		this.total = cart.total(customer.getDiscount());
		this.id = id;
		this.shipType = shipType;
		this.customer = customer;
		this.date = date;
		this.shipDate = shipDate;
		this.status = status;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.cc = cc;
		this.lines = new ArrayList<OrderLine>();
        
        for(CartLine cartLine : cart.getLines()) {
			lines.add(new OrderLine(cartLine.getProduct(), cartLine.getQty(), customer.getDiscount(), comment));
		}
        
        validate();
    }
    
    private void validate() {
        // Validações
        Objects.requireNonNull(customer, "Customer cannot be null");
        Objects.requireNonNull(date, "Date cannot be null");
        Objects.requireNonNull(billingAddress, "Billing address cannot be null");
        Objects.requireNonNull(shippingAddress, "Shipping address cannot be null");
        Objects.requireNonNull(lines, "Order lines cannot be null");
        Objects.requireNonNull(this.cc, "cc must not be null");
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Order lines cannot be empty");
        }
        if (this.subTotal < 0 || this.tax < 0 || this.total < 0) {
            throw new IllegalArgumentException("Subtotal, tax, and total must be positive values");
        }      
	}
        
    Order(int id, Customer customer, Date date, double subTotal, double total, List<OrderLine> lines, String comment, 
			ShipType shipType, Date shipDate, OrderState status, Address billingAddress, Address shippingAddress, CCTransaction cc) {
    	this.subTotal = 0;
		this.tax = 0;
		this.total = 0;
		this.id = id;
		this.shipType = shipType;
		this.customer = customer;
		this.date = date;
		this.shipDate = shipDate;
		this.status = status;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.cc = cc;
		this.lines = lines;
		validate();
	}
  

    public int getId() {
        return id;
    }
    

    public Customer getCustomer() {
        return customer;
    }

    public Date getDate() {
        return new Date(date.getTime()); // Retorna uma cópia para imutabilidade
    }
    
    public Date getShipDate() {
		return shipDate;
	}

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }
    
	public ShipType getShipType() {
		return shipType;
	}
	
	public CCTransaction getCc() {
		return cc;
	}
	

    public List<OrderLine> getLines() {
    	return Collections.unmodifiableList(lines);
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    public OrderState getStatus() {
        return this.status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }
   
	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", customerName=" + customer.getUname() + "}";
	}
}
