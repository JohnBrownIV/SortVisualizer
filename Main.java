import java.io.*;
import java.util.Scanner;

public class Main{

 public static void main(String[] args) throws FileNotFoundException {
  
  //Scanner in = new Scanner(System.in);
  //System.out.println("# of players: ");
  Scanner in = new Scanner(System.in);
  System.out.println("How many items to sort?");
  int arrLength = Integer.parseInt(in.nextLine());
  System.out.println("1: Bubble, 2: Insertion, 3: Selection, 4: Merge, 5: Bogo, 6: Quick");
  int sort = Integer.parseInt(in.nextLine());
  MyFrame frame = new MyFrame(arrLength, sort);//Integer.parseInt(in.next())
  //System.out.println("OPEN THE GAME HURRY");
  
 }
}