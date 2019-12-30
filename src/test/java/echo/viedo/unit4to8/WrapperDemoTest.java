package echo.viedo.unit4to8;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class WrapperDemoTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WrapperDemoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( WrapperDemoTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testWrapperDemo()
    {	String numStr = "20 78 9 -7 88 36 29";
		System.out.println("Before sort:" + numStr);
		numStr = WrapperDemo.sortStringNumber(numStr);
		System.out.println("After sort:" + numStr);
        assertTrue( true );
    }
}
