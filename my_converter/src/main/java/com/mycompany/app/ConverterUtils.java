package com.mycompany.app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;

public class ConverterUtils {
  public static class NewNumber {
    protected BigDecimal value;
    protected String unit;
    
    public NewNumber(BigDecimal value, String unit) {
      this.value = value;
      this.unit = unit;
    }

    public void printInputNumber(){
      System.out.println("Input: " + this.value + " " + this.unit);
    }
  }

  public static class Lengths extends NewNumber{
    public static final List<String> LENGTH_UNITS = Arrays.asList("km", "m", "dm", "cm", "mm", "miles");

    public Lengths(BigDecimal lengthValue, String lengthUnit) {
      super(lengthValue, lengthUnit);
    }

  }

  public static class NumberFactory {
    public static NewNumber createNewNumber( BigDecimal value, String unit ){
      if (Lengths.LENGTH_UNITS.contains(unit)) {
        return new Lengths(value, unit);
      } else {
        return null;
      }
    }
  }
}
