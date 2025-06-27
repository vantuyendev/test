public class CaseCake {
    public static void cake(int iterations) {
        System.out.println("=== Testing Case/Switch with Cake ===");
        
        for (int i = 0; i < iterations; i++) {
            char c = (char) (Math.random() * 26 + 'a');
            System.out.print("Character '" + c + "' is: ");
            
            switch(c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    System.out.println("Vowel"); 
                    break;
                case 'y':
                case 'w':
                    System.out.println("Sometimes a vowel"); 
                    break;
                default:
                    System.out.println("Not a vowel");
            }
        }
        
        System.out.println("=== End Testing Case/Switch with Cake ===\n");
    }
}
