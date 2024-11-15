package com.mycompany.app;

import java.math.BigDecimal;
import java.util.Scanner;

import com.mycompany.app.ConverterUtils.*;

/**
 * Hello world!
 */
public class App {

  /**
  * Die Hauptmethode, die als Einstiegspunkt für die Anwendung dient.
  * Sie gibt "Hello World!" auf der Konsole aus und führt eine einfache
  * Addition von zwei Zahlen durch.
  *
  * @param args Die Befehlszeilenargumente.
  */
  public static void main(String[] args) {
    System.out.println("Hello World!");

    System.out.println("Enter a value and a unit.");
    
    Scanner scannerValue = new Scanner(System.in);
    System.out.println("Value: ");
    BigDecimal value = scannerValue.nextBigDecimal();
  
    Scanner scannerUnit = new Scanner(System.in);
    System.out.println("Unit: ");
    String unit = scannerUnit.nextLine();

    NewNumber inputNumber = NumberFactory.createNewNumber(value, unit);


    inputNumber.printNumber();

    if ( inputNumber instanceof ConverterUtils.Lengths) {
      ConverterUtils.Lengths lengthInput = (ConverterUtils.Lengths) inputNumber;
      lengthInput.printLengthUnits();
      lengthInput.convertLength();
      lengthInput.printNumber();
    }

    scannerValue.close();
    scannerUnit.close();
  }
}
