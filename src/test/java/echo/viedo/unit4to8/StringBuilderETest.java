package echo.viedo.unit4to8;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StringBuilderETest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StringBuilderETest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( StringBuilderETest.class );
    }

    public void testArrayToString1()
    {
        int [] arr = {1,4,3,5,6,7,9};
        System.out.println("arr:" + "\t" + arr);
        // 用String，字符串拼接
        String str = StringBuilderE.arrayToString1(arr);
        assertEquals("[1,4,3,5,6,7,9]", str);

    }


    public void testArrayToString2()
    {
        int [] arr = {1,4,3,5,6,7,9};

        StringBuilder str2 = StringBuilderE.arrayToString2(arr);
        assertEquals("[1,4,3,5,6,7,9]", str2.toString());

    }
}
