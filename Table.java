import structure5.Association;
import structure5.Vector;

class Table {
  
  //Instance Variable
  private Vector<Association<String, FrequencyList>> numOfChars = new Vector<Association<String, FrequencyList>>();
  
  //public Table() {
  //}
  
  
  /*Adds the two character pair to the Vector numOfChars if it isn't already in there.
    If it is already in there, the following character ch will be checked in FrequencyList
    to see if it is in there or not. 
    @param str - charPair
    @param ch - character that follows ch
    @param b - vector bag that contains all the characters that follow the charPair
  */
  
  public void add(String str, String ch, Vector<String> b) {
    int i = numOfChars.indexOf(new Association<String, FrequencyList>(str, null));
    if (i != -1) { 
      (numOfChars.get(i).getValue()).add(ch);
      //System.out.println("Already there");
    }
    else {
      FrequencyList fl = new FrequencyList(str, ch, b);
      fl.add(ch);
      numOfChars.add(new Association<String, FrequencyList>(str, fl));
    }
  }
  
  /*Overriding the get method from Vector class
   * @param i - specific index of Association<String, FrequencyList>
   * @returns specific Association<String, FrequencyList> 
   */
  public Association<String, FrequencyList> get(int i) {
    return numOfChars.get(i);
  }
  
  
  /*Returns the size of the Table.
   */ 
  public int size() {
    return numOfChars.size();
  }
  
  /*Returns the index of where the charPair is in the FrequencyList.
   * @returns index
   */ 
  public int indexOf(String str) {
    int i = 0;
    int index = -1;
    while (i < numOfChars.size()) {
      if (numOfChars.get(i).getKey().equals(str))
        index = i;
      i++;
    }
    return index;
  }
  
  /*Returns the Table in String form. 
   * @returns Table in String form.
   */ 
  public String toString() {
    StringBuilder keys = new StringBuilder();
    for (int i=0; i<numOfChars.size(); i++) {
      keys.append("| " + numOfChars.get(i).getKey() + " " + numOfChars.get(i).getValue() + "\n");
    }
    return keys.toString();
  }
  
}
