import main.java.*;
import main.java.Bear;
import main.java.Clothing;
import main.java.Embroidery;
import main.java.NoiseMaker;
import main.java.Stuffing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import main.java.BearWorkshop;

import static org.junit.Assert.*;

/***
 * This class provides a framework to implement black box tests for various
 * implementations of the BearWorkshop Class. The BearWorkshop is having a
 * blowout sale and is offering the following savings.
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    private Class<BearWorkshop> classUnderTest;

    @SuppressWarnings("unchecked")
    public GivenBlackBox(Object classUnderTest) {
        this.classUnderTest = (Class<BearWorkshop>) classUnderTest;
    }

    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {BearWorkshop1.class},
                {BearWorkshop2.class},
                {BearWorkshop3.class},
                {BearWorkshop4.class},
                {BearWorkshop5.class}

        };
        return Arrays.asList(classes);
    }

    private BearWorkshop createBearWorkshop(String name) throws Exception {
        Constructor<BearWorkshop> constructor = classUnderTest.getConstructor(String.class);
        return constructor.newInstance(name);
    }

    BearWorkshop oneBear;
    Double oneBearExpected;

    BearWorkshop threeBears;
    Double threeBearsExpected;

    BearWorkshop twoBears;
    Double twoBearsExpected;

    @Before
    public void setUp() throws Exception {
        
    }

    @After
    public void tearDown() throws Exception {
    }

    // sample test

    /**
     * Test examines a BearFactory with 1 simple bear in it. The bear costs $30
     * and there are no savings.
     */
    @Test
    public void oneBearNoSavings() {
    	// One Bear base stuffing, no saving expected
        
        BearWorkshop oneBear = null;
        try {
        	oneBear = createBearWorkshop("NY");
        } catch (Exception e) {
        }
        
        oneBear.addBear(new Bear(Stuffing.stuffing.BASE)); // $30 stuffing + $1 casing -- should be no savings at all
        oneBearExpected = 0.00; // no savings since no clothing
    	
        Double ans = oneBear.calculateSavings();
        assertEquals(oneBearExpected, ans);
    }


    // sample test
    @Test
    public void threeBearsSaveOnCheapest() {
    	 // Three Bears expected to not pay for cheapest one
    	BearWorkshop threeBears = null;
        try {
        	threeBears = createBearWorkshop("AZ");
        } catch (Exception e) {
        }
    	
        threeBears.addBear(new Bear(Stuffing.stuffing.BASE)); // this is the cheapest one
        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
        threeBearsExpected = 31.00;

        Double ans = threeBears.calculateSavings();
        assertEquals(threeBearsExpected, ans);
    }

    // sample test
 
    @Test
    public void oneBearTest3clothings() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("DC");
        } catch (Exception e) {
        }
        
        Bear customBear = new Bear(Stuffing.stuffing.BASE); // $31
        bears.addBear(customBear);

	    customBear.clothing.add(new Clothing(4, "Hat")); //$35
	    customBear.clothing.add(new Clothing(4, "Sunglasses")); //$39
	    customBear.clothing.add(new Clothing(4, "Shoes")); // free
	    
        Double bearsExpected = 4.0; // one cloth item for free
        Double ans = bears.calculateSavings();
        assertEquals(bearsExpected, ans, 0.005);
    }

    @Test
    public void singleNormalBearNoSavingTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear = new Bear(Stuffing.stuffing.BASE); // $31
        bears.addBear(baseBear);

        double expectedSavings = 0.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void twoNormalBearNoSavingTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.BASE); // $31
        bears.addBear(baseBear1);
        bears.addBear(baseBear2);

        double expectedSavings = 0.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void threeNormalBearsSaveOnCheapestTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear3 = new Bear(Stuffing.stuffing.BASE); // $31
        bears.addBear(baseBear1);
        bears.addBear(baseBear2);
        bears.addBear(baseBear3);

        double expectedSavings = 31.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void fourNormalBearsSaveOnCheapestTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear3 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear4 = new Bear(Stuffing.stuffing.BASE); // $31
        bears.addBear(baseBear1);
        bears.addBear(baseBear2);
        bears.addBear(baseBear3);
        bears.addBear(baseBear4);

        double expectedSavings = 31.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void threeBearWithDifferentStuffingTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.DOWN); // $41
        Bear baseBear3 = new Bear(Stuffing.stuffing.FOAM); // $51
        bears.addBear(baseBear1);
        bears.addBear(baseBear2);
        bears.addBear(baseBear3);

        double expectedSavings = 31.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void threeBearWithAccessoryOnSingleBearTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear3 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.clothing.add(new Clothing(15, "Custom Cloth"));

        bears.addBear(baseBear1);
        bears.addBear(baseBear2);
        bears.addBear(baseBear3);

        double expectedSavings = 41.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWithThreeClothsTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());

        bears.addBear(baseBear1);

        double expectedSavings = 4.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWithThreeClothsIncludingCustomClothTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing(10, "Custom Cloth"));

        bears.addBear(baseBear1);

        double expectedSavings = 4.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void threeBearWithThreeClothsEachTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear3 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear2.clothing.add(new Clothing());
        baseBear2.clothing.add(new Clothing());
        baseBear2.clothing.add(new Clothing());
        baseBear3.clothing.add(new Clothing());
        baseBear3.clothing.add(new Clothing());
        baseBear3.clothing.add(new Clothing());

        bears.addBear(baseBear1);
        bears.addBear(baseBear2);
        bears.addBear(baseBear3);

        double expectedSavings = 51.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWithEmbroideryTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.ink = new Embroidery("TOM");

        bears.addBear(baseBear1);

        double expectedSavings = 0.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearCostingMoreThan70FreeEmbroideryTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.FOAM); // $51
        baseBear1.noisemakers.add(new NoiseMaker("Heart", "OK", NoiseMaker.Location.CENTERBODY));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.LEFT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.RIGHT_HAND));
        baseBear1.clothing.add(new Clothing());
        baseBear1.ink = new Embroidery("TOM");

        bears.addBear(baseBear1);

        double expectedSavings = 3.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearCostingMoreThan70WithDiscountedClothsTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.FOAM); // $51
        baseBear1.noisemakers.add(new NoiseMaker("Heart", "OK", NoiseMaker.Location.CENTERBODY));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.LEFT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.RIGHT_HAND));
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.ink = new Embroidery("TOM");

        bears.addBear(baseBear1);

        double expectedSavings = 7.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearCostingMoreThan70BeforeDiscountedClothsTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.FOAM); // $51
        baseBear1.noisemakers.add(new NoiseMaker("Heart", "OK", NoiseMaker.Location.CENTERBODY));
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.ink = new Embroidery("TOM");

        bears.addBear(baseBear1);

        double expectedSavings = 7.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWith10PaidAccessoriesForDiscountTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.noisemakers.add(new NoiseMaker("Heart", "OK", NoiseMaker.Location.CENTERBODY));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.LEFT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.RIGHT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LL", "OK", NoiseMaker.Location.LEFT_FOOT));
        baseBear1.noisemakers.add(new NoiseMaker("RL", "OK", NoiseMaker.Location.RIGHT_FOOT));
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());

        bears.addBear(baseBear1);

        double expectedSavings = 16.10;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWith10NonPaidAccessoriesForNoDiscountTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.noisemakers.add(new NoiseMaker("Heart", "OK", NoiseMaker.Location.CENTERBODY));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.LEFT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.RIGHT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LL", "OK", NoiseMaker.Location.LEFT_FOOT));
        baseBear1.noisemakers.add(new NoiseMaker("RL", "OK", NoiseMaker.Location.RIGHT_FOOT));
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());

        bears.addBear(baseBear1);

        double expectedSavings = 8.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWith10NonPaidAccessoriesWithEmbroideryTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.noisemakers.add(new NoiseMaker("Heart", "OK", NoiseMaker.Location.CENTERBODY));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.LEFT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LH", "OK", NoiseMaker.Location.RIGHT_HAND));
        baseBear1.noisemakers.add(new NoiseMaker("LL", "OK", NoiseMaker.Location.LEFT_FOOT));
        baseBear1.noisemakers.add(new NoiseMaker("RL", "OK", NoiseMaker.Location.RIGHT_FOOT));
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.clothing.add(new Clothing());
        baseBear1.ink = new Embroidery("TOM");

        bears.addBear(baseBear1);

        double expectedSavings = 11.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void moreThan10BearsSavingMultipleOf3Test() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear2 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear3 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear4 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear5 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear6 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear7 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear8 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear9 = new Bear(Stuffing.stuffing.BASE); // $31
        Bear baseBear10 = new Bear(Stuffing.stuffing.BASE); // $31

        bears.addBear(baseBear1);
        bears.addBear(baseBear2);
        bears.addBear(baseBear3);
        bears.addBear(baseBear4);
        bears.addBear(baseBear5);
        bears.addBear(baseBear6);
        bears.addBear(baseBear7);
        bears.addBear(baseBear8);
        bears.addBear(baseBear9);
        bears.addBear(baseBear10);

        double expectedSavings = 93.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }

    @Test
    public void oneBearWithLongerEmbroideryTest() {
        BearWorkshop bears = null;
        try {
            bears = createBearWorkshop("AZ");
        } catch (Exception ignored) {
        }

        assertNotNull(bears);

        Bear baseBear1 = new Bear(Stuffing.stuffing.BASE); // $31
        baseBear1.ink = new Embroidery("HAPPY NEW YEAR");

        bears.addBear(baseBear1);

        double expectedSavings = 0.00;
        final double actualSavings = bears.calculateSavings();
        assertEquals(expectedSavings, actualSavings, 0.01);
    }
}
