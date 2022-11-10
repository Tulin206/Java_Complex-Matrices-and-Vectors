package exercise5;

import java.util.Scanner;

public class VectorC {
    private static Scanner scanner;
    private int size;


    //Store the vector elements in a one-dimensional (non-public) array of type double.
    private Complex [] elem;

    //constructor
    //read the number of size as well as the vector entries from keyboard input.
    public VectorC(){
      this(true);
    }

    //with passed number of size.
    public VectorC (int size){
      this.size = size;
      this.elem = new Complex [size];
    }

    //with passed number of array size. 
    //If the argument keyboardIn is true, read the vector entries from keyboard input.
    public VectorC(int size, boolean keyboardIn){
      this(size);
      if(keyboardIn == true){
        readVector();
      }
    }

    //read the number of rows and columns from keyboard input. 
    //If the argument keyboardIn is true, read the matrix entries(row-wise) from keyboard input.
    public VectorC(boolean keyboardIn){
      System.out.print("Enter the number of size: "); 
      this.size = scanner.nextInt();
      if(keyboardIn){
        readVector();
      }
    }

    //Method for input
    public void readVector(){
      this.elem = new Complex [size];
      System.out.println("Enter the elements of the array: ");
      for (int i=0; i<elem.length; i++){
        elem [i] = Complex.readComplex();
        // elem [i] = scanner.nextDouble();
      }
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public Complex[] getElem() {
      return elem;
    }

    public void setElem(Complex[] elem) {
      this.elem = elem;
    }

    //Method to compute the Euclidean inner product
    public Complex dot(VectorC other){
      Complex sum = new Complex();
      if (this.size == other.size){
        for (int i=0; i<elem.length; i++){
          // sum = sum + (this.elem[i] * other.elem[i]);
          sum = sum.add(this.elem[i].mul(other.elem[i]));
        }
      }
      return sum;
    }

    public double norm (){
      Complex sum = new Complex();
      double normalize;
      for (int i=0; i<elem.length; i++){
        sum = sum.add(this.elem[i].mul(this.elem[i]));
      }
      normalize = sum.abs();
      return normalize;
  }

  public VectorC add(VectorC other){
    VectorC temp = new VectorC(this.size);
    if (this.size == other.size){
      for (int i=0; i<elem.length; i++){
          temp.elem[i] = this.elem[i].add(other.elem[i]);
      }
    } else {
      System.out.println("Vectors are not equal");
    }
    return temp;
  }


  public VectorC sub(VectorC other){
    VectorC temp = new VectorC(this.size);
    if (this.size == other.size){
      for (int i=0; i<elem.length; i++){
          temp.elem[i] = this.elem[i].sub(other.elem[i]);
          // temp.elem[i] = this.elem[i] - other.elem[i];
      }
    } else {
      System.out.println("Vectors are not equal");
    }
    return temp;
  }
}