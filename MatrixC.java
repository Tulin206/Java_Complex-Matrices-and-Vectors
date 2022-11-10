// maybe import a package:
// import static Prog1Tools.IOTools.readDouble;
// import static Prog1Tools.IOTools.readInteger;
package exercise5;

import java.util.Scanner;

class MatrixC {

  private static Scanner scanner;

  // ... fill in your code here
  private int rows;
  private int cols;

  //Store the matrix elements in a two-dimensional (non-public) array of type double.
  private Complex[][] elem; 

  

  public int getRows() {
    return rows;
  }


  public void setRows(int rows) {
    this.rows = rows;
  }


  public int getCols() {
    return cols;
  }


  public void setCols(int cols) {
    this.cols = cols;
  }


  public Complex[][] getElem() {
    return elem;
  }


  public void setElem(Complex[][] elem) {
    this.elem = elem;
  }


  //constructor
  //read the number of rows and columns as well as the matrix entries row-wise from keyboard input.
  public MatrixC(){
    this(true);
  }
  

  //read the number of rows and columns from keyboard input. 
  //If the argument keyboardIn is true, read the matrix entries(row-wise) from keyboard input.
  public MatrixC(boolean keyboardIn){
    System.out.print("Enter the number of rows: "); 
    this.rows = scanner.nextInt();
    System.out.print("Enter the number of columns: "); 
    this.cols = scanner.nextInt();
    if(keyboardIn){
      readMatrix();
    }
  }


  //with passed number of rows and columns.
  public MatrixC (int rows, int cols){
    this.rows = rows;
    this.cols = cols;
    this.elem = new Complex [rows][cols];
  }


  //with passed number of rows and columns. 
  //If the argument keyboardIn is true, read the matrix entries (row-wise) from keyboard input.
  public MatrixC(int rows, int cols, boolean keyboardIn){
    this(rows, cols);
    if(keyboardIn == true){
      readMatrix();
    }
  }


  //create a (deep) copy of the passed matrix.
  public MatrixC(MatrixC other){
    this.rows = other.rows;
    this.cols = other.cols;
    this.elem = other.elem;
  }


  //Method for input
  public void readMatrix(){
    this.elem = new Complex [rows][cols];
    System.out.println("Enter the elements of the array: ");
    for (int i=0; i<elem.length; i++){
      for (int j=0; j<elem[i].length; j++){
        elem [i][j] = Complex.readComplex();
      }
    }
  }

  public String toString() {
    StringBuffer ret = new StringBuffer();

    ret.append("{ ");

    for (int i=0; i < elem.length; i++) {
      ret.append("{");

      for (int j=0; j < elem[i].length-1; j++) {
        ret.append(elem[i][j]).append(", ");
      }

      ret.append(elem[i][elem[i].length-1]).append("}");
      if (i+1 < elem.length ) {
        ret.append(";\n");
      }
    }
    ret.append(" }");
    return ret.toString();
  }


  //Method for print
  public void print(String str){
    System.out.println(str + this.toString());
  }
  

  //method for reading the row value
  public int getRowCount(){
    return rows;
  }


  //method for reaidng the column value
  public int getColCount(){
    return cols;
  }


  //instance method

  // public MatrixC add(MatrixC other){
  //   for (int i=0; i<elem.length; i++){
  //     for (int j=0; j<elem[i].length; j++){
  //       temp.elem[i][j] = this.elem[i][j] + other.elem[i][j];
  //     }
  //   }
  //   return temp;
  // }

  public MatrixC add(MatrixC other){
    MatrixC temp = new MatrixC(this.rows, this.cols);
    if ((this.rows == other.rows) && (this.cols == other.cols)){
      for (int i=0; i<elem.length; i++){
        for (int j=0; j<elem[i].length; j++){
          temp.elem[i][j] = this.elem[i][j].add(other.elem[i][j]);
        }
      }
    } else {
      System.out.println("Matrices are not equal");
    }
    return temp;
  }


  public MatrixC sub(MatrixC other){
    MatrixC temp = new MatrixC(this.rows, this.cols);
    if ((this.rows == other.rows) && (this.cols == other.cols)){
      for (int i=0; i<elem.length; i++){
        for (int j=0; j<elem[i].length; j++){
          temp.elem[i][j] = this.elem [i][j].sub(other.elem [i][j]);
        }
      }
    } else {
      System.out.println("Matrices are not equal");
    }
    
    return temp;
  }


  public MatrixC transpose(){
    int temp = this.cols;
    this.cols = this.rows;
    this.rows = temp;
    Complex[][] arr= new Complex [rows][cols];
    for (int i=0; i<arr.length; i++){
      for (int j=0; j<arr[i].length; j++){
        arr[i][j] = this.elem[j][i];
      }
    }
    this.elem = arr;
    return this;
  }


  public MatrixC mul(double scalar){
    for (int i=0; i<elem.length; i++){
      for (int j=0; j<elem[i].length; j++){
        this.elem[i][j] = this.elem [i][j].mul(new Complex(scalar));
      }
    }
    return this;
  }


  public MatrixC symm(){
    return (this.add(this.transpose())).mul(0.5);
  }


  public MatrixC antiSymm(){
    return (this.sub(this.transpose())).mul(0.5);
  }

  public boolean equals(MatrixC other){
    return this.rows == other.rows && this.cols == other.cols && this.elem == other.elem;
  }


  public boolean isSymm(){
    return this.equals(this.transpose());
  }


  public boolean isAntiSymm(){
    return this.equals(this.transpose().mul(-1));
  }

  // Matrix-Vector Product
  public VectorD mul(VectorD vector){
    VectorD resultVector = new VectorD(elem.length);
    if (this.cols == vector.getSize()){
      Complex[] result = new Complex[elem.length];
      for (int i=0; i<elem.length; i++){
        Complex sum = new Complex();
        for (int j=0; j<elem[i].length; j++){
          sum = sum.add((this.elem[i][j].mul(vector.getElem()[j])));
        }
        // System.out.println(sum);
        result[i] = sum;
      }
      resultVector.setElem(result);
    }

    return resultVector;
  }



  public static void test(MatrixC A, MatrixC B) {
    System.out.println("MatrixC Test started");

    System.out.println("A is " + A.getRowCount() + "x" + A.getColCount() + " matrix");
    A.print("A");

    System.out.println("B is " + B.getRowCount() + "x" + B.getColCount() + " matrix");
    B.print("B");

    System.out.println("A==B?: " + A.equals(B) + " ... B==A?: " + B.equals(A));
    System.out.println("A.isSymm(): " + A.isSymm() + " ... A.isAntiSymm(): " + A.isAntiSymm());
    System.out.println("B.isSymm(): " + B.isSymm() + " ... B.isAntiSymm(): " + B.isAntiSymm());

    A.transpose().print("A.transpose()");
    B.transpose().print("B.transpose()");

    A.symm().print("A.symm()");
    B.symm().print("B.symm()");

    A.antiSymm().print("A.antiSymm()");
    B.antiSymm().print("B.antiSymm()");

    A.add(B).print("A+B");
    B.add(A).print("B+A");

    A.sub(B).print("A-B");
    B.sub(A).print("B-A");

    A.transpose().print("A.transpose()");
    B.transpose().print("B.transpose()");

    System.out.println("A==(A.symm() + A.antiSymm()): " + A.equals(A.symm().add(A.antiSymm())));
    System.out.println("B==(B.symm() + B.antiSymm()): " + B.equals(B.symm().add(B.antiSymm())));

    System.out.println("A+A==A.mul(2.0): " + A.mul(2.0).equals(A.add(A)));
    System.out.println("B+B==B.mul(2.0): " + B.mul(2.0).equals(B.add(B)));

    System.out.println("MatrixC Test finished");
  }

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    MatrixC B = new MatrixC(2,2, true);
    MatrixC A = new MatrixC(2,2, true);

    test(A, B);
    scanner.close();
  }
}

