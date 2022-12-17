
// Name: Manasse Bosango
// Date: 05/24/20222
// Classs: CS210
// Reason: Understand the importance and the use of file in java and programming
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class PigLatinTranslator {

   /**
    * This function takes in a Scanner input and a PrintStream output and
    * translates the input into pig
    * latin and prints it out to the output
    * 
    * @param input  a Scanner that reads from the input file
    * @param output PrintStream
    */
   public static void pigLatin(Scanner input, PrintStream output) {

      while (input.hasNextLine()) {

         String text = input.nextLine().toLowerCase();
         String str = stripSpecialCharaters(text);
         String saveAsToken = "";
         String pigLatinWord = "";

         // Checking if the string has a next token and if it does it will save it as a
         // token and
         // check if
         // it is a vowel. If it is a vowel it will add it to the pigLatinWord. If it is
         // not a vowel it will check
         // if the string has a vowel and if it does it will add it to the pigLatinWord.
         Scanner ScanneString = new Scanner(str);
         while (ScanneString.hasNext()) {
            saveAsToken = ScanneString.next();
            if (isVowel(saveAsToken.charAt(0))) {
               pigLatinWord = saveAsToken + "way" + " ";

            } else {
               boolean findVowel = true;
               for (int i = 1; i < saveAsToken.length() && findVowel; i++) {
                  if (isVowel(saveAsToken.charAt(i))) {
                     findVowel = false;
                     pigLatinWord = saveAsToken.substring(i) + saveAsToken.substring(0, i) + "ay" + " ";
                  }
               }
            }
            // Printing the pigLatinWord to the output file.
            output.print(pigLatinWord);
            // System.out.print(pigLatinWord);
         }
         output.println();
         // System.out.println();
      }
   }

   /**
    * This function takes a string and returns a string with all the numbers
    * removed
    * 
    * @param text the text to be stripped of numbers
    * @return A string with no numbers.
    */
   public static String stripNumbers(String text) {

      Scanner ScanneString = new Scanner(text);
      String saveAsToken = "";
      String fixedString = "";

      // Checking if the string has a next token and if it does it will save it as a
      // token and check if
      // it is a number. If it is not a number it will add it to the fixedString.
      while (ScanneString.hasNext()) {
         saveAsToken = ScanneString.next();

         if (saveAsToken.matches("[^0-9]+")) {
            fixedString += " " + saveAsToken;
            // System.out.print(fixedString);
         }
      }
      // System.out.println();
      return fixedString;
   }

   /**
    * It takes a string, strips out all numbers, then strips out all special
    * characters, and returns
    * the string
    * 
    * @param text The text that you want to strip the special characters from.
    * @return The method is returning a string that has been stripped of special
    *         characters.
    */
   public static String stripSpecialCharaters(String text) {

      String st = stripNumbers(text);
      Scanner ScanneString = new Scanner(st);
      String saveAsToken = "";
      String fixedString = "";
      while (ScanneString.hasNext()) {
         saveAsToken = ScanneString.next();

         // Checking if the string contains a period and if it does it will add the
         // string to the
         // fixedString.
         if (saveAsToken.contains(".")) {
            fixedString += " " + saveAsToken.substring(0, saveAsToken.indexOf("."));
            // System.out.print(fixedString);
         } else if (saveAsToken.contains(",")) {
            fixedString += " " + saveAsToken.substring(0, saveAsToken.indexOf(","));
            // System.out.print(fixedString);

         } else if (saveAsToken.contains("?")) {
            fixedString += " " + saveAsToken.substring(0, saveAsToken.indexOf("?"));
            // System.out.print(fixedString);
         } else if (saveAsToken.contains("!")) {
            fixedString += " " + saveAsToken.substring(0, saveAsToken.indexOf("!"));
            // System.out.print(fixedString);
         } else {
            fixedString += " " + saveAsToken;
            // System.out.print(fixedString);
         }
      }
      // System.out.println();
      return fixedString;
   }

   public static boolean isVowel(char ch) {
      // Checking if the character is a vowel.
      return (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o'
            || ch == 'U' || ch == 'u');
   }

   // method from BJP 5(Bluild Java Program 5) book
   /**
    * It asks the user for a file name, and if the file can't be read, it asks
    * again
    * 
    * @param console Scanner object that reads from the console
    * @return A Scanner object
    */
   public static Scanner getInput(Scanner console)
         throws FileNotFoundException {

      System.out.print("Input file name of the form (input.txt)! ");
      File f = new File(console.nextLine());
      while (!f.canRead()) {
         System.out.println("File not found. Try again.");
         System.out.print("Input file name of the form (input.txt)! ");
         f = new File(console.nextLine());
      }
      return new Scanner(f);
   }

   public static void main(String[] args) throws FileNotFoundException {
      // Creating a new Scanner object that reads from the console.
      Scanner console = new Scanner(System.in);

      // Asking the user for a file name, and if the file can't be read, it asks
      // again.
      Scanner input = getInput(console);

      // Creating a new file called input-PL.txt and it is saving the output of the
      // pigLatin method
      // in it.
      PrintStream output = new PrintStream("input-PL.txt");

      // Calling the pigLatin method and passing the input and output as parameters.
      pigLatin(input, output);
   }
}
