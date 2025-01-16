import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {

Timer timer;
int[] arr;
int sortType;
boolean sorting;
int selected;
boolean step;

int index;
int secondIndex;
 
  MyPanel(int arrLength, int sort) {
    arr = new int[arrLength];
    for (int i = 0; i < arrLength; i++) {
      arr[i] = i;
    }
    mixUp();
    System.out.println("Starting Array: " + Arrays.toString(arr));

    timer = new Timer(5, this);
	  timer.start();

    sortType = sort;
    sorting = false;

    index = 0;
    secondIndex = 0;

    this.setPreferredSize(new Dimension(1920,1080));
    this.setBackground(Color.white);
  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    
  }
  //NEW FRAME
  @Override
	public void actionPerformed(ActionEvent e) {
    if (checkSort()) {
      System.out.println("Good job! Ya did it!");
    } else {
      if (sortType == 1) {//Bubble
        bubSort();
      }
      System.out.println(Arrays.toString(arr));
    }
  }
  private void bubSort() {
    if (index > arr.length - 1) {
      index = 0;
    }
    if (arr[index] > arr[index + 1]) {
      swap(index, index + 1);
    }
    ++index;
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