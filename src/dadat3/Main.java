package dadat3;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    SquareMatrix A = new SquareMatrix(1000);
    SquareMatrix B = new SquareMatrix(1000);
    
    //System.out.println(Long.toString(System.currentTimeMillis()));
    //System.out.println(Long.toString(System.currentTimeMillis()));
    
    long init_time = System.currentTimeMillis();
    SquareMatrix C = A.multiplyMatrix(B);
    long final_time = System.currentTimeMillis();
    System.out.println("Tiempo para calcular C: " + Long.toString(final_time - init_time) + " milisegundos.");
    
    Random rng = new Random();
    rng.setSeed(System.currentTimeMillis());
    
    MultChecker checker = new MultChecker();
    //C.mutateRandom(rng, 1);
    
    //int buenos = 0;
    //for (int i = 0; i < 100; i++) {
    //  rng.nextInt();
    //  if (checker.checkOnce(rng, A, B, C)) {
    //    System.out.println("Malo");
    //  } else {
    //    System.out.println("Bueno");
    //    buenos++;
    //  }
    //}
    //System.out.println(Integer.toString(buenos));
    //DEBUG nada, linea para testear github
    
    SquareMatrix MC = new SquareMatrix(1000);
    boolean perfection;
    int k = 1;
    int accepted;
    boolean last_check;
    
    for (int m = 1; m <= 10; m++) {
      MC.setValues(C.getValues());
      //MC.mutateRandom(rng, 100*m);
      MC.mutateRandom(rng, m); //PELIGRO DEBUG
      perfection = false;
      k = 1;
      
      while(!perfection) {
        perfection = true;
        accepted = 0; //dice que esta bien cuando deveria estar malo
        for (int i = 0; i < 100; i++) { //Deberia ser 100 y no 1000
          last_check = checker.checkMany(rng, k, A, B, MC);
          rng.nextInt();
          if (last_check) {
            //failure
            perfection = false;
            accepted++;
          }
        }
      System.out.println("Caso con m:");
      System.out.println(Integer.toString(m));
      System.out.println("Y k:");
      System.out.println(Integer.toString(k));
      System.out.println("Cantidad de errores:");
      System.out.println(Integer.toString(accepted));
      k++;
      }
    }
    
  }
}
