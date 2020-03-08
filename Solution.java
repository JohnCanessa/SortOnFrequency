import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * 
 */
public class Solution {


  /**
   * Sort hash map by value in descending order.
   * This method runs in -> O(n log(n)) time.
   */
  static List<Entry<Character, Integer>> sortHMByValue(HashMap<Character, Integer> hm) {

    // **** array list with hash map entries (needs to be sorted) ****
    List<Entry<Character, Integer>> sortedEntries = new ArrayList<Entry<Character, Integer>>(hm.entrySet());

    // **** sort the array list -> O(n log(n)) ****
    Collections.sort( sortedEntries,
                      new Comparator<Entry<Character, Integer>>() {
                        @Override
                        public int compare(Entry<Character, Integer> e1, Entry<Character, Integer> e2) {
                          return e2.getValue().compareTo(e1.getValue());
                        }
                      });

    // **** array list sorted by value in ascending order ****
    return sortedEntries;
  }


  /**
   * Given a string, sort it in increasing order based on the frequency of its characters.
   * This method runs in -> O(n log(n)) time.
   */
  static String sortString(String str) {

    // **** check the string ****
    if ((str == null) || (str.length() <= 0)) {
      return "";
    }

    // **** hash map used to determine character frequency ****
    HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

    // *** populate the hashmap used to get the frequency of the characters -> O(n) ****
    for (int i = 0; i < str.length(); i++) {

      // **** for ease of use ****
      char ch = str.charAt(i);

      // **** key in hash map ****
      if (hm.containsKey(ch)) {
        int count = hm.get(ch);
        hm.put(ch, ++count);
      }
      
      // **** key not in hashmap ****
      else {
        hm.put(ch, 1);
      }
    }

    // **** sort the hash map in descending order by value -> O(n log(n)) ****
    List<Entry<Character, Integer>> al = sortHMByValue(hm);

    // **** string builder to generate string ****
    StringBuilder sb = new StringBuilder();

    // **** populate the string builder -> O(n) ****
    for (int i = 0; i < al.size(); i++) {

      // **** for ease of use ****
      Entry<Character, Integer> e = al.get(i);

      // **** generate char[] with the specified character ****
      char[] chs = new char[e.getValue()];
      Arrays.fill(chs, e.getKey());

      // **** append the char[] to the string builder ****
      sb.append(chs);
    }

    // **** return string ****
    return sb.toString();
  }


  /**
   * Given a string, sort it in increasing order based on the frequency of its characters.
   * This method runs in -> O(n)) time.
   * The code is shorter than in the sortString method.
   */
  static String frequencyString(String str) {

    // **** check the string ****
    if ((str == null) || (str.length() <= 0)) {
      return "";
    }

    // **** used to build the string to return ****
    StringBuilder sb = new StringBuilder();

    // **** populate the hashmap used to get the frequency of the characters -> O(n) ****
    HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
    for (char c : str.toCharArray()) {
      hm.put(c, hm.getOrDefault(c, 0) + 1);
    }
    
    // **** declare a priority queue with Comparator ****
    PriorityQueue<Character> pq = new PriorityQueue<Character>(new Comparator<Character>() {
      @Override
      public int compare(Character a, Character b) {
        return hm.get(b) - hm.get(a);
      }
    });

    // **** populate the priority queue -> O(n) ****
    for (char c : hm.keySet()) {
      pq.add(c);      // O(log(n))
    }

    // **** populate the string builder -> O(n) **** 
    while (!pq.isEmpty()) {

      // **** get the next character from the priority queue ****
      char c = pq.remove();

      // **** get the count from the hash map ****
      int count = hm.get(c);

      // **** append this character to string builder the specified count ****
      for (int i = 0; i < count; i++) {
        sb.append(c);
      }
    }

    // **** return the string ****
    return sb.toString();
  }


  /**
   * Test scaffolding.
   */
  public static void main(String[] args) {
    
    // **** open scanner ****
    Scanner sc = new Scanner(System.in);

    // **** read the number of strings to process ****
    int n = Integer.parseInt(sc.nextLine());

    // **** process each string ****
    for (int i = 0; i < n; i++) {

      // **** read the string ****
      String str = sc.nextLine();

      // **** sort the characters in the string and display result ****
      System.out.println(sortString(str));
      System.out.println(frequencyString(str));
    }

    // **** close scanner ****
    sc.close();
  }
}