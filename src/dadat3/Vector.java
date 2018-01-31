package dadat3;

import java.util.Random;

public class Vector {
  
  private int size;
  private long[] values;
  
  public Vector(int n) {
    this.size = n;
  }
  
  public long[] getValues() {
    return this.values;
  }
  
  public boolean checkValue(long value, int index) {
    return (value == values[index]);
  }
  
  public void setValues(long[] param_values) {
    this.values = param_values;
  }
  
  public boolean checkEquals(Vector vect) {
    for (int i = 0; i < size; i++) {
      if (!vect.checkValue(this.values[i], i)) {
        return false;
      }
    }
    return true;
  }
  
  public void setRandomValues(Random rng) {
    this.values = new long[this.size];
    for (int i = 0; i < this.size; i++) {
      this.values[i] = Math.abs(rng.nextLong()) % 2;
    }
  }
}
