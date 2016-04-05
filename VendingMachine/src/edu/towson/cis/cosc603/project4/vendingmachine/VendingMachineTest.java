/**
 * 
 */
package edu.towson.cis.cosc603.project4.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jess Rudolph
 * 
 * The unit test Class for VendingMachine.
 *
 */

public class VendingMachineTest {
	
	/**
	 * Declares necessary test objects for VendingMachine.
	 */
	VendingMachine snacksVendingMachine;
	
	VendingMachineItem doritos, utz, skittles, popcorn;
	
	/**
	 * Initializes the test objects for the test cases.
	 * 
	 * Fills the vending machine such that slots with code A, B, & C are occupied.
	 * 
	 * @throws Exception the exception
	 */
	@Before
		public void setUp() throws Exception {
				
		doritos = new VendingMachineItem("Doritos", 1.75);
		utz = new VendingMachineItem("Utz", 1.50);
		skittles = new VendingMachineItem("Skittles", 1.85);
		popcorn = new VendingMachineItem("Buttery Popcorn", 2.00);
		
		snacksVendingMachine = new VendingMachine();
		
		snacksVendingMachine.addItem(doritos, "A");
		snacksVendingMachine.addItem(utz, "B");
		snacksVendingMachine.addItem(skittles, "C");
		}
	
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc603.project4.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * 
	 * Adds item to slot with code labeled "D", since that is the last empty slot to be filled.
	 * 
	 * Test checks that the item was added to the correct slot.
	 */
	@Test
	public void testAddItem() {
		snacksVendingMachine.addItem(popcorn, "D");
		assertSame(popcorn, snacksVendingMachine.getItem("D"));
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc603.project4.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * 
	 *Test will prevent item from being added to a slot that is already occupied by an item.
	 */
	@Test(expected=VendingMachineException.class)
	public void testAddItemAlreadyOccupied() {
		snacksVendingMachine.addItem(popcorn, "C");
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc603.project4.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * 
	 * Test will through an Exception for trying to add an item to a slot with an invalid code.
	 * 
	 */
	@Test(expected=VendingMachineException.class)
	public void testAddItemSlotNotExist() {
		snacksVendingMachine.addItem(popcorn, "H");
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#removeItem(java.lang.String)}.
	 * 
	 * Removes the item from the slot with code "B".
	 * 
	 * Tests to verify that the item requested to be removed is the item that was removed.
	 */
	@Test
	public void testRemoveItem() {
		assertSame(utz, snacksVendingMachine.removeItem("B"));
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#removeItem(java.lang.String)}.
	 * 
	 * Test will verify that Exception is thrown because a null item cannot be removed.
	 * 
	 */
	@Test(expected=VendingMachineException.class)
	public void testRemoveItemNull() {
		snacksVendingMachine.removeItem("D");
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#insertMoney(double)}.
	 * 
	 * Tests that the amount of money inserted matches the balance.
	 */
	@Test
	public void testInsertMoney() {
		snacksVendingMachine.insertMoney(1.00);
		assertEquals(1.00, snacksVendingMachine.getBalance(), 0.00);
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#insertMoney(double)}.
	 * 
	 * Tests throws and exception because the amount of money entered is a negative number.
	 */
	@Test(expected=VendingMachineException.class)
	public void testInsertMoneyNegative() {
		snacksVendingMachine.insertMoney(-1.00);
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#insertMoney(double)}.
	 * 
	 * Test that the balance of inserting multiple bills and coins is correct.
	 */
	@Test
	public void testInsertMoneyMultiple() {
		snacksVendingMachine.insertMoney(1.00);
		snacksVendingMachine.insertMoney(1.25);
		snacksVendingMachine.insertMoney(.10);
		assertEquals(2.35, snacksVendingMachine.getBalance(), 0.00);
	}

	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#getBalance()}.
	 * 
	 * First verifies that the beginning balance is 0.
	 * Verifies the balance is the amount inserted.
	 *
	 */
	@Test
	public void testGetBalance() {
		assertEquals(0, snacksVendingMachine.getBalance(), 0);
		snacksVendingMachine.insertMoney(1.00);
		assertEquals(1.00, snacksVendingMachine.getBalance(), 0.0);		
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#getBalance()}.
	 * 
	 * First verifies that the beginning balance is 0.
	 * 
	 * Tests that the balance is the amount of change due.
	 *
	 */
	@Test
	public void testGetBalanceOfChangeDue() {
		assertEquals(0, snacksVendingMachine.getBalance(), 0);
		
		snacksVendingMachine.insertMoney(1.00);
		snacksVendingMachine.insertMoney(1.00);		
		snacksVendingMachine.makePurchase("B");	
		assertEquals(.50, snacksVendingMachine.getBalance(), 0.00);
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * 
	 * User inserts money and makes a purchase.
	 */
	@Test
	public void testMakePurchase() {
		snacksVendingMachine.insertMoney(1.00);
		snacksVendingMachine.insertMoney(1.00);		
		assertTrue(snacksVendingMachine.makePurchase("C"));	
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * 
	 * User tries to purchase an item code that has no product (aka it is null).
	 */
	@Test
	public void testMakePurchaseNull() {
		snacksVendingMachine.removeItem("B");
		snacksVendingMachine.insertMoney(1.00);
		snacksVendingMachine.insertMoney(1.00);		
		assertFalse(snacksVendingMachine.makePurchase("B"));	
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * 
	 * There isn't enough money to purchase the item.
	 */
	@Test
	public void testMakePurchaseNotEnoughMoney() {
		snacksVendingMachine.insertMoney(1.00);	
		snacksVendingMachine.insertMoney(.25);		
		assertFalse(snacksVendingMachine.makePurchase("A"));	
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * 
	 * Test to see false is returned when user tries to purchase an item without inserting money.
	 */
	@Test
	public void testMakePurchaseNoMoney() {	
		assertFalse(snacksVendingMachine.makePurchase("B"));	
	}
	

	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#returnChange()}.
	 * 
	 * There's no change to return.
	 */
	@Test
	public void testReturnChange() {
		assertEquals(0.00, snacksVendingMachine.returnChange(), 0.00);
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#returnChange()}.
	 * 
	 * Tests the correct change is given after purchase.
	 * Verifies the balance is set to 0.
	 */
	@Test
	public void testReturnChangeForPurchase() {
		snacksVendingMachine.insertMoney(1.00);
		snacksVendingMachine.insertMoney(1.00);		
		snacksVendingMachine.makePurchase("B");	
		assertEquals(.50, snacksVendingMachine.returnChange(), 0.00);
		assertEquals(0.00, snacksVendingMachine.getBalance(), 0.00);
		}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc603.project4.vendingmachine.VendingMachine#returnChange()}.
	 * 
	 * Tests the correct change is given after purchase.
	 * Verifies the balance is set to 0.
	 */
	@Test
	public void testReturnChangeWithoutPurchase() {
		snacksVendingMachine.insertMoney(1.00);
		snacksVendingMachine.insertMoney(1.00);		
		assertEquals(2.00, snacksVendingMachine.returnChange(), 0.00);
		assertEquals(0.00, snacksVendingMachine.getBalance(), 0.00);
		}
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		snacksVendingMachine = null;
		doritos = null; 
		utz = null;
		skittles = null;
		popcorn = null;
	}

}
