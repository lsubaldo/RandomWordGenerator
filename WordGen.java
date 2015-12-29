import structure5.Association;
import structure5.Vector;
import java.util.Random; 
import java.io.*; 
import java.util.Scanner;

class WordGen {
  //Instance Variables 
  public static final int LENGTH_OF_CHARS = 2;
  public static final int NUM_TO_STOP = 40;
  private static Vector<String> charList = new Vector<String>(); 
  private static Random randomGen = new Random();
  private static Table charPairs = new Table();
  
  //Defaults for Command Line Arguments
  private static String filename = "default.txt";
  private static String charPair = "th";
  private static int num = NUM_TO_STOP;
  
  /*
   * Creates table by executing Table and FrequencyList classes
   */
  public static void createTable(String p) {
    for (int i=0; i<p.length()-LENGTH_OF_CHARS; i++) {
      String currentPair = p.substring(i, i+LENGTH_OF_CHARS);
      String character = p.substring(i+LENGTH_OF_CHARS, i+LENGTH_OF_CHARS+1);
      Vector<String> bag = new Vector<String>();
      //System.out.println(currentPair + " " + character);
      charPairs.add(currentPair, character, bag);   //add method from Table
    }
    System.out.println(charPairs.toString());
  }
  
  
  public static void main(String[] args) { 
    
    //Handling Command-Line Arguments 
    if (args.length == 1) {
      filename = args[0];
    }
    if (args.length == 2) {
      filename = args[0];
      charPair = args[1];
    }
    if (args.length == 3) {
      filename = args[0];
      charPair = args[1];
      num = Integer.parseInt(args[2]);
    }
    try {
      //Scanning the File
      StringBuilder textFromFile = new StringBuilder();
      Scanner sc = new Scanner(new File(filename));
      while (sc.hasNextLine()) {
        textFromFile.append(sc.nextLine()); 
      }
      sc.close();
      
      //Turning the Text from File to String
      String text = textFromFile.toString();
      //System.out.println(text); - for debugging purposes 
      //Creating a table 
      createTable(text); 
      
      //Random Word Generator
      StringBuilder randomWord = new StringBuilder();
      randomWord.append(charPair);   //initializing the random word with the starting charPair

      int i=0;
      String randomChar = "";
      while (i < num) { 
        int index = charPairs.indexOf(charPair); 
        //System.out.println(index);
        if (index == -1) {
          break;
          //twoCharPair = randomWord.substring(0, LENGTH_OF_CHARS);
        //  continue;
        }
        else {
          FrequencyList freq = charPairs.get(index).getValue(); 
          //System.out.println(freq.toString());
          randomChar = freq.randomCh(); 
          randomWord.append(randomChar);
          charPair = randomWord.substring(i+1, i+LENGTH_OF_CHARS+1);
          i++;
        }
        //System.out.println(i + " " + charPair); 
        
      }
    System.out.println(randomWord.toString());
    }
    catch (FileNotFoundException e) {
      System.err.println("error: file does not exist");
      return; 
    }
  }
}
