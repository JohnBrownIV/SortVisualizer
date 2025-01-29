import java.util.*;
import java.io.*;

public class QuickSort {
  static int[] quiSort(int[] arr) {
    return quiSort(arr, 0, arr.length - 1);
  }
  static int[] quiSort(int[] arr, int lowIndex, int highIndex) {
    if (arr.length <= 1) {
      return arr;
    }
    if (lowIndex == highIndex) {
      //making sub arrays
      int[] sub1 = new int[lowIndex];
      for (int i = 0; i < sub1.length; i++) {
        sub1[i] = arr[i];
      }
      int[] sub2 = new int[arr.length - sub1.length];
      for (int i = 0; i < sub2.length; i++) {
        sub2[i] = arr[i + sub1.length];
      }
      sub1 = quiSort(sub1, 0, sub1.length - 1);
      sub2 = quiSort(sub2, 0, sub2.length - 1);
      //Copy onto array
      for (int i = 0; i < sub1.length; i++) {
        arr[i] = sub1[i];
      }
      for (int i = 0; i < sub2.length; i++) {
        arr[i + sub1.length] = sub2[i];
      }
      return arr;
    }
    if (arr[lowIndex] <= arr[highIndex]) {//advanced with a smaller array
      return quiSort(arr, lowIndex + 1, highIndex);
    }
    //swap
    int temp = arr[lowIndex];
    arr[lowIndex] = arr[highIndex - 1];
    arr[highIndex - 1] = arr[highIndex];
    arr[highIndex] = temp;
    return quiSort(arr, lowIndex, highIndex - 1);
  }
  public static void main(String[] args) throws IOException {
    //Bubble sort
    Scanner in = new Scanner(System.in);
    System.out.println("What File will be read?");
    File read = new File(in.nextLine());
    Scanner reader = new Scanner(read);
    reader = new Scanner(read);
    int lines = Integer.parseInt(reader.nextLine());
    int[] arr1 = new int[lines];
    for (int i = 0; i < lines; i++) {
      arr1[i] =  Integer.parseInt(reader.nextLine());
    }
    //Printing:
    arr1 = quiSort(arr1);
    for (int i = 0; i < arr1.length - 1; i++) {
      System.out.print(arr1[i] + ", ");
    }
    System.out.print(arr1[arr1.length - 1]);
  }
}