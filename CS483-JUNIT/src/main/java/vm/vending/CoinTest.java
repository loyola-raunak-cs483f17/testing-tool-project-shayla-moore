package vm.vending;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import vm.exceptions.InvalidCoinException;
import vm.interfaces.Denomination;
import vm.vending.Coin;

public class CoinTest {
	private Coin coinNone, coinHalf, coinQuarter, coinDime, coinNickel;
	
	@Before
public void beforeEveryMethod() throws InvalidCoinException {
	coinNone = new Coin();
	
	coinHalf = new Coin('h');
	
	coinQuarter = new Coin('q');
	
	coinDime = new Coin('d');
	
	coinNickel = new Coin('n');
}

	@After
	public void doAfterEveryTestMethod() {
		coinNone = null;
		
		coinHalf = null;
		
		coinQuarter = null;
		
		coinDime = null;
		
		coinNickel = null;
	}
	
	@Test
	public void testCoin() throws InvalidCoinException {
		
		assertNotNull(coinNone);
		
		
	}

	@Test
	public void testCoinChar() {
		
		assertNotNull(coinHalf);
	
		
		
		assertNotNull(coinQuarter);
	
		
		
		assertNotNull(coinDime);
	
		
		
		assertNotNull(coinNickel);
		
		
	}
	
	
	@Test(expected = InvalidCoinException.class)
	public void testSetDenominationInvalid() throws InvalidCoinException {
		char invalidDenom = 'Z';
		Coin invalidCoin = new Coin();
		invalidCoin.setDenomination(invalidDenom);
	}
	

	@Test
	public void testCoinDenomination() {
		Coin coinHalfDenom = new Coin(Denomination.Half);
		assertNotNull(coinHalfDenom);
		
		Coin coinDimeDenom = new Coin(Denomination.Dime);
		assertNotNull(coinDimeDenom);
		
		Coin coinQuarterDenom = new Coin(Denomination.Quarter);
		assertNotNull(coinQuarterDenom);
		
		Coin coinNickelDenom = new Coin(Denomination.Nickel);
		assertNotNull(coinNickelDenom);
		
		Coin coinNoneDenom = new Coin(Denomination.None);
		assertNotNull(coinNoneDenom);
		
	}

	@Test
	public void testGetDenomination() {
		assertEquals(Denomination.None, coinNone.getDenomination());
		assertEquals(Denomination.Half, coinHalf.getDenomination());
		assertEquals(Denomination.Quarter, coinQuarter.getDenomination());
		assertEquals(Denomination.Dime, coinDime.getDenomination());
		assertEquals(Denomination.Nickel, coinNickel.getDenomination());
	}

	@Test
	public void testGetCharValue() {

		assertEquals(Character.UNASSIGNED, coinNone.getCharValue());
		assertEquals('h', coinHalf.getCharValue());
		assertEquals('q', coinQuarter.getCharValue());
		assertEquals('d', coinDime.getCharValue());
		assertEquals('n', coinNickel.getCharValue());
	}

	@Test
	public void testSetDenominationDenomination() {
		
		Coin coinNickelDenom = new Coin();
		coinNickelDenom.setDenomination(Denomination.Nickel);
		assertEquals(Denomination.Nickel, coinNickelDenom.getDenomination());
		
		Coin coinDimeDenom = new Coin();
		coinDimeDenom.setDenomination(Denomination.Dime);
		assertEquals(Denomination.Dime, coinDimeDenom.getDenomination());
		
		Coin coinQuarterDenom = new Coin();
		coinQuarterDenom.setDenomination(Denomination.Quarter);
		assertEquals(Denomination.Quarter, coinQuarterDenom.getDenomination());
		
		Coin coinHalfDenom = new Coin();
		coinHalfDenom.setDenomination(Denomination.Half);
		assertEquals(Denomination.Half, coinHalfDenom.getDenomination());
		
		Coin coinNoneDenom = new Coin();
		coinNoneDenom.setDenomination(Denomination.None);
		assertEquals(Denomination.None, coinNoneDenom.getDenomination());
		
	
	}

	@Test
	public void testGetValue() {
		
		
		assertEquals(0, coinNone.getValue());
		assertEquals(5, coinNickel.getValue());
		assertEquals(10, coinDime.getValue());
		assertEquals(25, coinQuarter.getValue());
		assertEquals(50, coinHalf.getValue());

		
	}

	@Test
	public void testIsValid() {

		coinNone.setDenomination(Denomination.None);
		assertFalse(coinNone.isValid());
	
		coinDime.setDenomination(Denomination.Dime);
		assertTrue(coinDime.isValid());
	}
	

}
