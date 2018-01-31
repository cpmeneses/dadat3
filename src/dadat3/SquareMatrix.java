package dadat3;

import java.util.Random;

public class SquareMatrix {
  private int size;
  private long[][] values;
  
  public SquareMatrix(int n) {
    this.values = new long[n][n];
    size = n;
  }
  
  public long[][] getValues() {
    return this.values;
  }
  
  public long getValue(int row, int column) {
    return values[row][column];
  }
  
  public void setValues(long[][] param_values) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        values[i][j] = param_values[i][j];
      }
    }
  }
  
  public void fillRandom(Random rng) {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        values[i][j] = (Math.abs(rng.nextLong()) % 20001) - 10000;
      }
    }
  }
  
  public void mutateRandom(Random rng, int to_mutate) {
    int to_check_left = size * size;
    int to_mutate_left = to_mutate;
    int dice_roll;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        dice_roll = rng.nextInt(to_check_left);
        if (dice_roll < to_mutate_left) {
          //mutate cell
          values[i][j] = (Math.abs(rng.nextLong()) % 20001) - 10000;
          to_mutate_left--;
        }
        to_check_left--;
      }
    }
  }
  
  public boolean compare(SquareMatrix sm) {
    long[][] smv = sm.getValues();
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (smv[i][j] != this.values[i][j]){
          return false;
        }
      }
    }
    return true;
  }
  
  private long[][] multiplyArray(long[][] m1, long[][] m2) {
    int m_size = m1.length;
    long[][] res = new long[m_size][m_size];
    int sum;
    for (int i = 0; i < m_size; i++) {
      for (int j = 0; j < m_size; j++) {
        sum = 0;
        for (int k = 0; k < m_size; k++) {
          sum += m1[i][k] * m2[k][j];
        }
        res[i][j] = sum;
      }
    }
    return res;
  }
  
  public SquareMatrix multiplyMatrix(SquareMatrix sm) {
    SquareMatrix res = new SquareMatrix(this.size);
    long[][] matrix_res = this.multiplyArray(this.values, sm.getValues());
    res.setValues(matrix_res);
    return res;
  }
  
  private long[] multiplyVectorArray(long[][] mtrx, long[] vect) {
    int m_size = mtrx.length;
    long[] res = new long[m_size];
    int sum;
    for (int i = 0; i < m_size; i++) {
      sum = 0;
      for (int k = 0; k < m_size; k++) {
        sum += mtrx[i][k] * vect[k];
      }
      res[i] = sum;
    }
    return res;
  }

  public Vector multiplyVector(Vector vect) {
    Vector res = new Vector(this.size);
    long[] vector_res = this.multiplyVectorArray(this.values, vect.getValues());
    res.setValues(vector_res);
    return res;
  }


  
}
