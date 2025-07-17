// No import statements needed for classes in the same package

import p1.Book;
import p1.BreakAndContinue;
import p1.CaseCake;
import p1.Flower;
import p1.Leaf;
import p1.Number;
import p1.PassObject;
import p1.Person;
import p1.SwitchCake;
import p1.randNum;
import testp1.CastingTest;
import testp1.ShortCircuitTest;
import testp1.StringTest;
import testp1.TestNumber;

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
        Person.testPerson();
        
        //call test User (Practice 3)
        User.testUser();
        
        //call test Time (Practice 3)
        Time.testTime();
        
        //call test Recursion (Practice 3)
        Recursion.testRecursion(); 

        //call test NameNumber
        NameNumber nameNumber = new NameNumber("Tuyen", "123456789");
        NNCollection nnCollection = new NNCollection();
        nnCollection.insert(nameNumber);
        String foundNumber = nnCollection.findNumber("Tuyen");
        System.out.println("Found number: " + foundNumber);

    }
}