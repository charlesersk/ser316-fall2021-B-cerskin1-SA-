package test.java;

import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import main.java.BearWorkshop;


public class GivenWhiteBox {
    BearWorkshop oneBear;

    @Before
    public void setUp() throws Exception {
    }


    /**
     * test was wrong.
     * the expected value should be the purchased cost after taxes.
     */
    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        double ans = oneBear.checkout();
        assertEquals(33.17, ans, 0.005);
    }

    @Test
    public void getCostOneBearTest() {
        oneBear = new BearWorkshop("AZ");
        double ans = oneBear.getCost(new Bear());
        assertEquals(31, ans, 0.005);
    }

    @Test
    public void getCostOneBearNoFreeClothsTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear = new Bear();
        bear.clothing.add(new Clothing());
        bear.clothing.add(new Clothing());
        double ans = oneBear.getCost(bear);
        assertEquals(39, ans, 0.005);
    }

    @Test
    public void getCostOneBearWithFreeClothsTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear = new Bear();
        bear.clothing.add(new Clothing());
        bear.clothing.add(new Clothing());
        bear.clothing.add(new Clothing());
        double ans = oneBear.getCost(bear);
        assertEquals(39, ans, 0.005);
    }

    @Test
    public void getCostOneBearWithCenterBodyNoiseTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear = new Bear();
        bear.addNoise(new NoiseMaker());
        double ans = oneBear.getCost(bear);
        assertEquals(41, ans, 0.005);
    }

    @Test
    public void getCostOneBearWithoutInkPriceTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear = new Bear();
        bear.clothing.add(new Clothing(50, "Important cloth"));
        bear.clothing.add(new Clothing(60, "High Quality"));
        bear.ink = new Embroidery("Hello Tom");
        double ans = oneBear.getCost(bear);
        assertEquals(141, ans, 0.005);
    }

    @Test
    public void checkoutWithFreeBearTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear1 = new Bear();
        final Bear bear2 = new Bear();
        final Bear bear3 = new Bear();
        oneBear.addBear(bear1);
        oneBear.addBear(bear2);
        oneBear.addBear(bear3);
        double ans = oneBear.checkout();
        assertEquals(66.34, ans, 0.005);
    }

    /**
     * fix: add getter and setter for customer in BearWorkshop.
     */
    @Test
    public void checkoutRestrictForAgeLessThan14Test() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        oneBear.setCustomer(new Customer(13, "AZ", null));
        double ans = oneBear.checkout();
        assertEquals(-1, ans, 0.005);
    }

    @Test
    public void checkoutRestrictForAgeLessThan14AndParentIsAlsoLessThan18Test() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        oneBear.setCustomer(new Customer(13, "AZ", new Customer(17, "AZ", null)));
        double ans = oneBear.checkout();
        assertEquals(-1, ans, 0.005);
    }

    @Test
    public void checkoutPassForValidParentAgeTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        oneBear.setCustomer(new Customer(13, "AZ", new Customer(21, "AZ", null)));
        double ans = oneBear.checkout();
        assertEquals(33.17, ans, 0.005);
    }

    @Test
    public void checkoutFromNYStateTest() {
        oneBear = new BearWorkshop("NY");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        double ans = oneBear.checkout();
        assertEquals(33.79, ans, 0.005);
    }

    @Test
    public void checkoutFromVAStateTest() {
        oneBear = new BearWorkshop("VA");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        double ans = oneBear.checkout();
        assertEquals(32.55, ans, 0.005);
    }

    @Test
    public void checkoutFromDCStateTest() {
        oneBear = new BearWorkshop("DC");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        double ans = oneBear.checkout();
        assertEquals(34.255, ans, 0.005);
    }

    @Test
    public void checkoutFromCAStateTest() {
        oneBear = new BearWorkshop("CA");
        final Bear bear1 = new Bear();
        oneBear.addBear(bear1);
        double ans = oneBear.checkout();
        assertEquals(34.10, ans, 0.005);
    }


    @Test
    public void getCostOneBearWithMultipleNoiceTest() {
        oneBear = new BearWorkshop("AZ");
        final Bear bear = new Bear();
        bear.addNoise(new NoiseMaker());
        bear.addNoise(new NoiseMaker("OK", "OK OK", NoiseMaker.Location.LEFT_HAND));
        bear.addNoise(new NoiseMaker("OK", "OK OK", NoiseMaker.Location.RIGHT_FOOT));
        bear.addNoise(new NoiseMaker("OK", "OK OK", NoiseMaker.Location.LEFT_FOOT));
        bear.addNoise(new NoiseMaker("OK", "OK OK", NoiseMaker.Location.RIGHT_HAND));
        double ans = oneBear.getCost(bear);
        assertEquals(61.0, ans, 0.005);
    }
}
