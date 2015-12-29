import structure5.Association;
import structure5.Vector;
import java.util.Random; 

class FrequencyList {
  
  //Instance Variables
  private String twoCharPair; 
  private String character;
  private int total;  //total number of times the twoCharPair occurs in the file
  private Vector<Association<String, Integer>> nextCharCount = new Vector<Association<String, Integer>>();
  private Vector<String> charListBag = new Vector<String>(); 
  private Random randomGen = new Random();
  
  /*
   * Constructor: keeps track of the twoCharPair, the ch that is being added
   * to its FrequencyList, and the charListBag that will help decide what
   * letter will be randomly generated. 
   * @param t - the two character pair
   * @param ch - the character that follows the two character pair
   * @param c - the vector that contains the characters that follow the two character pair
   */
  public FrequencyList(String t, String ch, Vector<String> c) {
    twoCharPair = t;
    character = ch;
    charListBag = c;
  }
  
  /*@param i - index 
   * @returns specific FrequencyList at a given index 
   */
  public Association<String, Integer> get(int i) {
    return nextCharCount.get(i); 
  }
  
  /*Returns specific character - a key in FreqeuncyList
   */
  public String getChar(int i) {
    return nextCharCount.get(i).getKey();
  }
  
  /*Returns the number of times the charPair appears in the text
   * @returns total 
   */
  public int getTotal() {
    return total;
  }
  
  /*Returns the probability of a character following the charPair.
   * @param i - index
   * @return probability
   */ 
  public float getProb(int i) {
    float prob = (float)nextCharCount.get(i).getValue()/total;
    prob = prob * 10000;
    int probRound = Math.round(prob);
    prob = (float)probRound/10000;
    return prob; 
    //return (float)nextCharCount.get(i).getValue()/total;
  }
  
  /*Returns the size of the FrequencyList.
   * @returns FrequencyList
   */ 
  public int size() {
    return nextCharCount.size();
  }
  
  /*Returns the charListBag that contains all the characters that occur
   * after the charPair t.
   * @param t - charPair
   * @returns charListBag
   */ 
  public Vector<String> getBag(String t) {
    return charListBag;
  }
  
  /*Returns a character that is randomly generated from the charListBag.
   * @returns random character
   */ 
  public String randomCh() {
    int randomNum = randomGen.nextInt(charListBag.size());
    String randomChar = charListBag.get(randomNum);
    return randomChar; 
  }
  
    /*Adds entry to Vector if character isn't already in there.
     * @param ch - character that follows the charPair
     */ 
  public void add(String ch) {
    total++;
    charListBag.add(ch); 
    for (int i=0; i<nextCharCount.size(); i++) {
      if (nextCharCount.get(i).getKey().equals(ch)) {
        nextCharCount.get(i).setValue(nextCharCount.get(i).getValue()+1);
        return;
      }
    }
    nextCharCount.add(new Association<String, Integer>(ch, 1));
  }
  
  /*Returns the String form of the FrequencyList. 
   * @returns FrequencyList in String form. 
   */ 
  public String toString() {
    StringBuilder freq = new StringBuilder();
    freq.append(" | ");
    for (int i=0; i<nextCharCount.size(); i++) {
      freq.append(nextCharCount.get(i).getKey() + ": " + getProb(i) + " ");
      freq.append("| ");
    }
    return freq.toString();
  }
}
