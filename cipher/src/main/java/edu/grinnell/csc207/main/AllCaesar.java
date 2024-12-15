package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Main class that encodes and decodes using the Caesar cipher.
 * Takes input to encode or decode the given string.
 * Course: CSC207Fa2024
 * Author: Bonsen Yusuf
 */
public class AllCaesar {

/**
 * Expected number of command-line parameters.
 */
  private static final int EXPECTED_NUM_PARAMS = 2;

/**
 * Main method to execute encoding or decoding.
 *
 * @param args Command-line arguments: encode/decode and the string.
 */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    /* Check if the correct number of parameters is entered. */
    if (args.length != EXPECTED_NUM_PARAMS) {
      System.err.println("Error: Incorrect number of parameters.");
      pen.close();
      return;
    } //if

    String choice = args[0];
    String str = args[1];

    /* Validate the choice of encoding or decoding. */
    if (!choice.equals("encode") && !choice.equals("decode")) {
      System.err.println("Error: Invalid option: \"" + choice + "\". Valid options are \"encode\" or \"decode\".");
      pen.close();
      return;
    } //if

    /* Validate input string. */
    if (!str.matches("[a-z]*")) {
      System.err.println("Error: String contains characters other than lowercase letters.");
      pen.close();
      return;
    } //if

    /* Perform encoding or decoding for all 26 possible shifts. */
    for (char ch = 'a'; ch <= 'z'; ch++) {
      String result = choice.equals("encode")
                        ? CipherUtils.caesarEncrypt(str, ch)
                        : CipherUtils.caesarDecrypt(str, ch);
      pen.printf("n = %c: %s%n", ch, result);
    } //for

    pen.close();
  } //main
} // AllCaesar


