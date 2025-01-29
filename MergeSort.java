import java.util.*;
import java.io.*;

public class MergeSort {
  static int[] mergSort(int[] arr) {
    if (arr.length <= 1) {
      return arr;
    }
    //Splitting
    int[] sub1 = new int[arr.length / 2];
    int[] sub2 = new int[arr.length - sub1.length];
    for (int i = 0; i < sub1.length; ++i) {
      sub1[i] = arr[i];
    }
    for (int i = 0; i < sub2.length; ++i) {
      sub2[i] = arr[i + sub1.length];
    }
    //Sorting Splits
    sub1 = mergSort(sub1);
    sub2 = mergSort(sub2);
    //Sorting the returned lists
    int[] re = new int[arr.length];
    int sub1Index = 0;
    int sub2Index = 0;
    for (int i = 0; i < arr.length; ++i) {
      if (sub1Index == sub1.length) {
        re[i] = sub2[sub2Index];
        ++sub2Index;
        continue;
      }
      if (sub2Index == sub2.length) {
        re[i] = sub1[sub1Index];
        ++sub1Index;
        continue;
      }
      if (sub1[sub1Index] < sub2[sub2Index]) {
        re[i] = sub1[sub1Index];
        ++sub1Index;
      } else {
        re[i] = sub2[sub2Index];
        ++sub2Index;
      }
    }
    //return
    return re;
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
    arr1 = mergSort(arr1);
    for (int i = 0; i < arr1.length - 1; i++) {
      System.out.print(arr1[i] + ", ");
    }
    System.out.print(arr1[arr1.length - 1]);
  }
}