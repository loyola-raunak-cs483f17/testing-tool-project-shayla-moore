package vm.vending;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import vm.interfaces.ICoin;
import vm.vending.Coin;
import vm.vending.CoinCalculator;

public class CoinCalculatorTest {
	List<ICoin> coinList;
	CoinCalculator coinCalc;
	
	Coin coinHalf, coinQuarter, coinDime, coinNickel, coinNone;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		coinNone = new Coin();
		
		coinHalf = new Coin('h');
		
		coinQuarter = new Coin('q');
		
		coinDime = new Coin('d');
		
		coinNickel = new Coin('n');
		coinCalc = new CoinCalculator();
	}

	@After
	public void tearDownAfterClass() throws Exception {
		coinNone = null;
		
		coinHalf = null;
		
		coinQuarter = null;
		
		coinDime = null;
		
		coinNickel = null;
		
		
		coinCalc.Clear();
	}
/**
	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}
**/
	@Test
	public void testCoinCalculator() {	
		assertNotNull(coinCalc);
	}

	@Test
	public void testAddCoinICoin() {
		
		coinCalc.AddCoin(coinHalf);
		assertEquals(50, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinQuarter);
		assertEquals(25, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinDime);
		assertEquals(10, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinNickel);
		assertEquals(5, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinNone);
		assertEquals(0, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinHalf);
		coinCalc.AddCoin(coinQuarter);
		coinCalc.AddCoin(coinDime);
		coinCalc.AddCoin(coinNickel);
		coinCalc.AddCoin(coinNone);
		assertEquals(90, coinCalc.TotalValue());
		coinCalc.Clear();
		
	}

	@Test
	public void testAddCoinICoinInt() {
		coinCalc.AddCoin(coinNickel, 2);
		assertEquals(10, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinDime, 2);
		assertEquals(20, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinQuarter, 2);
		assertEquals(50, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinHalf, 2);
		assertEquals(100, coinCalc.TotalValue());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinNone, 2);
		assertEquals(0, coinCalc.TotalValue());
		coinCalc.Clear();
	}

	@Test
	public void testIsValid() {
		coinCalc.AddCoin(coinNone);
		assertFalse(coinCalc.IsValid());
		coinCalc.Clear();
		
		coinCalc.AddCoin(coinDime);
		assertTrue(coinCalc.IsValid());
		
	}


}
