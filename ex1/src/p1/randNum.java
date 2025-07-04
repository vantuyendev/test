package p1;
import java.util.Random;
public class randNum {
public static int generateRandomNumber() {
 Random r = new Random();
// Generate random integers in range 0 to 999
 int num = r.nextInt(1000);
 // int num = r.nextDouble(1000);
return num;
 }
}