import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

public class MyPanel extends JPanel {

Timer timer;
int[] arr;
int sortType;
 
  MyPanel(int arrLength, int sort) {
    arr = new int[arrLength];
    for (int i = 0; i < arrLength; i++) {
      arr[i] = i;
    }
    System.out.println(Arrays.toString(arr));
    System.out.println(checkSort());
    mixUp();
    System.out.println(Arrays.toString(arr));
    System.out.println(checkSort());
    this.setPreferredSize(new Dimension(1920,1080));
    this.setBackground(Color.white);
    if (sortType == 1) {
      bubSort();
     }
  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    System.out.println(Arrays.toString(arr));
  }
  private void bubSort() throws InterruptedException {
    for (int i = 0; i < arr.length; i++) {
      for (int y = 0; y < arr.length - 1; y++) {
        if (arr[y] < arr[y] + 1) {
          swap(y, y + 1);
        }
        repaint();
        Thread.sleep(100);
      }
    }
  }
  private boolean checkSort() {
    boolean sorted = true;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < arr[i - 1]) {
        sorted = false;
        break;
      }
    }
    return sorted;
  }
  private void mixUp() {
    shuffle();
    //Reverse
    for (int i = 0; i < arr.length / 2; i++) {
      swap(i, (arr.length - 1) - i);
    }
    shuffle();
    //Reverse
    for (int i = 0; i < arr.length / 2; i++) {
      swap(i, (arr.length - 1) - i);
    }
    shuffle();
  }
  public void shuffle() {
    Random random = new Random();
    int count = arr.length;
    for (int i = count; i > 1; i--) {
        swap(i - 1, random.nextInt(i));
    }
  }
  private void swap(int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
  }
}