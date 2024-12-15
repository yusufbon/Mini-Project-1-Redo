
package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;
import java.io.PrintWriter;

/**
 * Main class for decoding and encoding using Caesar and Vigenere ciphers.
 * Takes four command-line arguments to perform the specified action.
 * Course: CSC207Fa2024
 * Author: Bonsen Yusuf
 */
public class Cipher {

/**
 * Expected number of command-line parameters.
 */
  private static final int EXPECTED_NUM_PARAMS = 4;

/**
 * Main method to decode and encode using Caesar or Vigenere cipher.
 *
 * @param args Command-line arguments for action, cipher type, string, and key.
 */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Print error message if wrong number of params received.
    if (args.length != EXPECTED_NUM_PARAMS) {
      System.err.printf("Error: Expected %d parameters, received %d.%n", EXPECTED_NUM_PARAMS, args.length);
      pen.close();
      return;
    } //if

    String choice = null;
    String str = null;
    String key = null;
    String typeOfCipher = null;

    // Check the arguments
    for (String arg : args) {
      if (arg.startsWith("-encode") || arg.startsWith("-decode")) {
        choice = arg;
      } else if (arg.startsWith("-caesar") || arg.startsWith("-vigenere")) {
        typeOfCipher = arg;
      } else if (str == null) {
        str = arg;
      } else {
        key = arg;
      } //if
    } //for

    // Check the cipher type
    if (typeOfCipher == null || (!typeOfCipher.equals("-caesar") && !typeOfCipher.equals("-vigenere"))) {
      System.err.println("Error: No valid cipher specified. Legal values are '-caesar' and '-vigenere'.");
      pen.close();
      return;
    } //if

    // Validate the string
    if (!str.matches("[a-z]*")) {
      System.err.println("Error: Strings must be only lowercase letters.");
      pen.close();
      return;
    } //if

    // Check which choice
    if (choice == null || (!choice.equals("-encode") && !choice.equals("-decode"))) {
      System.err.println("Error: No valid action specified. Legal values are '-encode' and '-decode'.");
      pen.close();
      return;
    } //if

    // Handle Caesar cipher
    if (typeOfCipher.equals("-caesar")) {
      if (key == null || key.length() != 1) {
        System.err.println("Error: Caesar ciphers require a one-character key.");
        pen.close();
        return;
      } //if

      char keyChar = key.charAt(0);
      String result;
      if (choice.equals("-encode")) {
        result = CipherUtils.caesarEncrypt(str, keyChar);
      } else {
        result = CipherUtils.caesarDecrypt(str, keyChar);
      } //if
      pen.println(result);

    } else if (typeOfCipher.equals("-vigenere")) {
        // Handle Vigenere cipher
      if (key == null || !key.matches("[a-z]+")) {
        System.err.println("Error: Vigenere keys must be lowercase letters.");
        pen.close();
        return;
      } //if

      String result;
      if (choice.equals("-encode")) {
        result = CipherUtils.vigenereEncrypt(str, key);
      } else {
        result = CipherUtils.vigenereDecrypt(str, key);
      } //if
      pen.println(result);
    } //else if

    pen.close();
  } //main
} // Cipher
