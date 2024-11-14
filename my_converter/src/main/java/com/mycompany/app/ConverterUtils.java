package com.mycompany.app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public static final List<String> LENGTH_UNITS = Arrays.asList(
      "km", "m", "dm", "cm", "mm", "miles"
    );
    public static final Map<String, BigDecimal> CONVERSION_FACTORS = new HashMap<>();
    static {
      CONVERSION_FACTORS.put("m", BigDecimal.valueOf(1.0));
      CONVERSION_FACTORS.put("dm", BigDecimal.valueOf(0.1));
      CONVERSION_FACTORS.put("cm", BigDecimal.valueOf(0.01));
      CONVERSION_FACTORS.put("mm", BigDecimal.valueOf(0.001));
      CONVERSION_FACTORS.put("km", BigDecimal.valueOf(1000.0));
      CONVERSION_FACTORS.put("miles", BigDecimal.valueOf(1609.34));
    }

    public Lengths(BigDecimal lengthValue, String lengthUnit) {
      super(lengthValue, lengthUnit);
    }

    public void printLengthUnits(){
      System.out.println("\nUnits to choose from: ");
      for (int i = 0; i < LENGTH_UNITS.size(); i++){
        System.out.println(LENGTH_UNITS.get(i));
      }
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
