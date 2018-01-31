package dadat3;

import java.util.Random;

public class MultChecker {
  
  public boolean checkOnce(Random rng, SquareMatrix A, SquareMatrix B, SquareMatrix C) {
    
    Vector rand_vect = new Vector(1000);
    rand_vect.setRandomValues(rng);
    Vector left_vector = A.multiplyVector(B.multiplyVector(rand_vect));
    Vector right_vector = C.multiplyVector(rand_vect);
    
    return left_vector.checkEquals(right_vector);
  }
  
  public boolean checkMany(Random rng, int times, SquareMatrix A, SquareMatrix B, SquareMatrix C) {
    for (int i = 0; i < times; i++) {
      if (!checkOnce(rng, A, B, C)) {
        return false;
      }
      rng.nextInt();
    }
    return true;
  }
  
}
