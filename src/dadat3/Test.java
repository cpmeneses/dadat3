package dadat3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class Test {

  //@org.junit.jupiter.api.Test
  //void test1() {
  //  fail("Not yet implemented");
  //}

  @org.junit.jupiter.api.Test
  void test_multiplyMatrix() {
    long[][] A = new long[2][2];
    A[0][0] = 2;
    A[0][1] = 5;
    A[1][0] = 3;
    A[1][1] = 7;
    long[][] B = new long[2][2];
    A[0][0] = 4;
    A[0][1] = 8;
    A[1][0] = 6;
    A[1][1] = 10;
    long[][] C = new long[2][2];
    A[0][0] = 38;
    A[0][1] = 66;
    A[1][0] = 54;
    A[1][1] = 94;
    SquareMatrix MA = new SquareMatrix(2);
    MA.setValues(A);
    SquareMatrix MB = new SquareMatrix(2);
    MB.setValues(B);
    SquareMatrix MC = MA.multiplyMatrix(MB);
    long[][] MC_values = MC.getValues();
    
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(MC_values[i][j], C[i][j], Integer.toString(i) +
        		Integer.toString(j) + "es distinto" + Long.toString(C[0][0]));
      }
    }
    
  }
  
  @org.junit.jupiter.api.Test
  void test_rng() {
    Random rng = new Random();
    rng.setSeed(System.currentTimeMillis());
    Vector vect = new Vector(100);
    vect.setRandomValues(rng);
    for (int i = 0; i < 100; i++) {
      //System.out.println(Long.toString(vect.getValues()[i]));
      if (vect.getValues()[i] != 0 && vect.getValues()[i] != 1) {
        fail("fuera del rango 0 o 1, en vez es: " + Long.toString(vect.getValues()[i]));
      }
    }
  }
  
  @org.junit.jupiter.api.Test
  void test_fillRandom() {
    Random rng = new Random();
    rng.setSeed(System.currentTimeMillis());
    SquareMatrix sm = new SquareMatrix(1000);
    
    sm.fillRandom(rng);
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        //System.out.println(Long.toString(sm.getValue(i, j)));
        if (sm.getValue(i, j) < -10000 || sm.getValue(i, j) > 10000) {
          fail("fuera de rango en vez es:" + Long.toString(sm.getValue(i, j)));
        }
      }
    }
  }
  
  //@org.junit.jupiter.api.Test
  void test_checkOnce() {
    Random rng = new Random();
    rng.setSeed(System.currentTimeMillis());
    
    MultChecker checker = new MultChecker();
    
    SquareMatrix A = new SquareMatrix(1000);
    A.fillRandom(rng);
    SquareMatrix B = new SquareMatrix(1000);
    B.fillRandom(rng);
    
    SquareMatrix C = A.multiplyMatrix(B);
    if(!checker.checkOnce(rng, A, B, C)) {
      fail("Deberia dar true");
    }
  }
  
  @org.junit.jupiter.api.Test
  void test_mutateRandom() {
    Random rng = new Random();
    rng.setSeed(System.currentTimeMillis());
    
    SquareMatrix A = new SquareMatrix(1);
    A.fillRandom(rng);
    long init_value = A.getValue(0, 0);
    A.mutateRandom(rng, 1);
    
    if(A.getValue(0, 0) == init_value) {
      fail("Tiene una probabilidad de 1 en 20001 de estar bueno");
    }
  }
  
  @org.junit.jupiter.api.Test
  void test_mutateRandom_checkSameAmountOfMutated() {
    Random rng = new Random();
    rng.setSeed(System.currentTimeMillis());
    
    SquareMatrix A = new SquareMatrix(100);
    A.fillRandom(rng);
    rng.nextInt();
    SquareMatrix B = new SquareMatrix(100);
    B.fillRandom(rng);
    rng.nextInt();
    
    SquareMatrix C = A.multiplyMatrix(B);
    SquareMatrix D = new SquareMatrix(100);
    D.setValues(C.getValues());
    D.mutateRandom(rng, 10);
    rng.nextInt();
    D.setValues(C.getValues());
    D.mutateRandom(rng, 10);
    rng.nextInt();
    
    long[][] matrix_c = C.getValues();
    long[][] matrix_d = D.getValues();
    
    int differences = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (matrix_c[i][j] != matrix_d[i][j]) {
          differences++;
        }
      }
    }
    if (differences != 10) {
      fail("La cantidad de diferencias deberia ser 10 pero es " + Integer.toString(differences));
    }
  }
}
