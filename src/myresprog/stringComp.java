/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myresprog;

import java.util.Comparator;

/**
 *
 * @author Daniel
 */
public class stringComp implements Comparator<String> {
  public int compare(String o1, String o2) {
    if (o1.length() < o2.length()) {
      return 1;
    } else if (o1.length() > o2.length()) {
      return -1;
    } else {
      return 0;
    }
  }
}
