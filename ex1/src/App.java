public class App {
    public static void main(String[] args) {

        // call test number
        TestNumber.testNumber();

        // call test String
        StringTest.test();

        // call Circuit test
        ShortCircuitTest.test();

        // call test casting
        CastingTest.testDouble();
        CastingTest.testFloat();
        CastingTest.testCasting();        // call Test if-else control
        CaseCake.cake(5);

        // call test break and continue
        // BreakAndContinue.main(new String[0]); // original main method
        BreakAndContinue.WhileTest(10);
        BreakAndContinue.DoWhileTest(10);

        // call test switch
        SwitchCake.switchCase();

        // call test random number        System.out.println("Random number: " + randNum.randNum());        // call test random number
        System.out.println("Random number: " + randNum.randNum());
        
        System.out.println("=== All tests completed successfully! ===");
    }
}