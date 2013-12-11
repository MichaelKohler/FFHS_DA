package ch.ffhs.da.uebung4;

import static org.junit.Assert.*;
import org.junit.Test;

public class DatumTest {

	@Test
    public void testDatum_DatumGetsCreated() {
        int year = 2012;
        int month = 12;
        int day = 15;
        Datum actual = new Datum(day, month, year);
        assertNotNull(actual);
    }
	
	@Test
    public void testDatum_nextIsCorrectWithinSameMonth() {
        int year = 2012;
        int month = 12;
        int day = 15;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(16, 12, 2012);
        Datum actual = date.next();
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_nextIsCorrect() {
        int year = 2012;
        int month = 11;
        int day = 30;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(1, 12, 2012);
        Datum actual = date.next();
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_nextIsCorrectWithYear() {
        int year = 2012;
        int month = 12;
        int day = 30;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(1, 1, 2013);
        Datum actual = date.next();
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_previousIsCorrectWithinSameMonth() {
        int year = 2012;
        int month = 12;
        int day = 16;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(15, 12, 2012);
        Datum actual = date.previous();
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_previousIsCorrect() {
        int year = 2012;
        int month = 12;
        int day = 1;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(30, 11, 2012);
        Datum actual = date.previous();
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_previousIsCorrectWithYear() {
        int year = 2013;
        int month = 1;
        int day = 1;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(30, 12, 2012);
        Datum actual = date.previous();
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_plusAddsCorrect() {
        int year = 2013;
        int month = 12;
        int day = 13;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(15, 12, 2013);
        Datum actual = date.plus(2);
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_plusAddsCorrectWithMonthAndYear() {
        int year = 2013;
        int month = 12;
        int day = 29;
        Datum date = new Datum(day, month, year);
        Datum expected = new Datum(2, 1, 2014);
        Datum actual = date.plus(3);
        assertNotNull(actual);
        assertTrue(actual.equal(expected));
    }
	
	@Test
    public void testDatum_betweenCalculatesCorrectlyWithinMonth() {
        Datum date = new Datum(5, 12, 2013);
        Datum date2 = new Datum(9, 12, 2013);
        int difference = date.between(date2);
        assertTrue(difference == 4);
    }
	
	@Test
    public void testDatum_betweenCalculatesCorrectly() {
        Datum date = new Datum(28, 12, 2013);
        Datum date2 = new Datum(3, 1, 2014);
        int difference = date.between(date2);
        assertTrue(difference == 5);
    }
    
    @Test
    public void testDatum_isBeforeWithinMonth() {
        Datum date = new Datum(28, 12, 2013);
        Datum date2 = new Datum(29, 12, 2013);
        boolean actual = date.is_before(date2);
        assertTrue(actual);
        Datum date3 = new Datum(29, 12, 2013);
        Datum date4 = new Datum(28, 12, 2013);
        boolean actual2 = date3.is_before(date4);
        assertFalse(actual2);
    }
    
    @Test
    public void testDatum_isBefore() {
        Datum date = new Datum(28, 12, 2013);
        Datum date2 = new Datum(1, 1, 2014);
        boolean actual = date.is_before(date2);
        assertTrue(actual);
        Datum date3 = new Datum(5, 1, 2014);
        Datum date4 = new Datum(28, 12, 2012);
        boolean actual2 = date3.is_before(date4);
        assertFalse(actual2);
    }

    @Test
    public void testDatum_isEqual() {
        Datum date = new Datum(28, 12, 2013);
        Datum date2 = new Datum(28, 12, 2013);
        assertTrue(date.equal(date2));
    }

}
