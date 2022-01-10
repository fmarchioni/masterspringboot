package com.sample;

public class Order {
String item;
int quantity;

public Order() {
	super();
	// TODO Auto-generated constructor stub
}

public Order(String item, int quantity) {
	super();
	this.item = item;
	this.quantity = quantity;

}

public String getItem() {
	return item;
}
public void setItem(String item) {
	this.item = item;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

@Override
public String toString() {
	return "Order [item=" + item + ", quantity=" + quantity + "]";
}


}
