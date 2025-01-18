import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    System.out.println("How many items to sort?");
    int arrLength = Integer.parseInt(in.nextLine());
    int[] arr = new int[arrLength];
    for (int i = 0; i < arrLength; i++) {
      arr[i] = i;
    }
    arr = mixUp(arr);
    System.out.println("Starting Array: " + Arrays.toString(arr));
    arr = quickSort(arr);
  }
  public static int[] quickSort(int[] arr) {
    int pivot = arr.length - 1;
    for (int i = pivot; i > 0; --i) {
      if (arr[i] < arr[pivot]) {
        swap(arr, pivot, pivot - 1);
        --pivot;
        swap(arr, i, pivot + 1);
      }
    }
    
    return arr;
  }
  //Shuffle stuff
  private static int[] mixUp(int arr[]) {
    arr = shuffle(arr);
    //Reverse
    for (int i = 0; i < arr.length / 2; i++) {
      arr = swap(arr, i, (arr.length - 1) - i);
    }
    arr = shuffle(arr);
    //Reverse
    for (int i = 0; i < arr.length / 2; i++) {
      arr = swap(arr, i, (arr.length - 1) - i);
    }
    arr = shuffle(arr);
    return arr;
  }
  public static int[] shuffle(int[] arr) {
    Random random = new Random();
    int count = arr.length;
    for (int i = count; i > 1; i--) {
        swap(arr, i - 1, random.nextInt(i));
    }
    return arr;
  }
  private static int[] swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    return arr;
}
}
