// No import statements needed for classes in the same package

public class App {
    public static void main(String[] args){

        //call test Number 
        Number.testNumber();
        
        //call test PassObject 
        PassObject.testPassObject();

        //call test testnumber
       
        TestNumber.testNumber();

        // call test String
        StringTest.test();

        // call Circuit test
   
        ShortCircuitTest.test();

        //call test casting

        CastingTest.testDouble();
        CastingTest.testFloat();
        CastingTest.testCasting();

        // call Test if-else control
       
        CaseCake.cake(10);
        
        //call test break and continue

        BreakAndContinue.WhileTest(10);
        BreakAndContinue.DoWhileTest(10);

        //call test switch
        SwitchCake.switchCase();
        
        //call test random number
        System.out.println(randNum.generateRandomNumber());

        //call test Book
        Book.testBook();

        //call test Leaf
        Leaf.testleaf();

        //call test Flower
        Flower.testFlower();

        //call test Person
     
    }
    }