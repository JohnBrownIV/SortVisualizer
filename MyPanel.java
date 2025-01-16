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
boolean done;
int countdown;

int index;
int secondIndex;
int min;
boolean sPhase2;

int barWidth;
int barHeight;
 
  MyPanel(int arrLength, int sort) {
    arr = new int[arrLength];
    for (int i = 0; i < arrLength; i++) {
      arr[i] = i;
    }
    mixUp();
    System.out.println("Starting Array: " + Arrays.toString(arr));

    timer = new Timer(1, this);
	  timer.start();
    countdown = 200;

    sortType = sort;
    sorting = false;
    done = false;

    index = 0;
    secondIndex = 0;
    sPhase2 = false;

    this.setPreferredSize(new Dimension(1920,1080));
    this.setBackground(Color.white);

    //figuring out dimensions
    barHeight = (960 / arrLength);
    barWidth = (1800 / arrLength);

  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.clearRect(0, 0, 1920, 1080);

    g2D.setColor(Color.black);
    g2D.fillRect(50, 980, 1800, 5);

    g2D.fillRect(50, 20 + arr[0] * barHeight, barWidth, 1000);

    for (int x = 0; x < arr.length; x++) {
      if (x == index) {
        g2D.setColor(Color.orange);
      } else {
        g2D.setColor(Color.black);
      }
     g2D.fillRect(50 + x * barWidth, 20 + arr[x] * barHeight, barWidth, 1000);
    }

  }
  //NEW FRAME
  @Override
	public void actionPerformed(ActionEvent e) {
    if (countdown == 0) {
      if (checkSort()) {
        if (!done) {
          System.out.println("Good job! Ya did it!");
          done = true;
        }
      } else {
        if (sortType == 1) {//Bubble
          bubSort();
        } else if (sortType == 2) {
          selectSort();
        }else if (sortType == 4) {//bogo
          shuffle();
        }
        System.out.println(Arrays.toString(arr));
        repaint();
      }
    } else {
      countdown--;
    }
  }
  private void selectSort() {
    if (index > arr.length) {
      index = 0;
    }
    if (secondIndex > arr.length) {
      secondIndex = index;
      sPhase2 = true;
    }
    if (!sPhase2) {
      if (arr[secondIndex] < min) {
        min = arr[secondIndex];
      }
    }
  }
  private void bubSort() {
    if (index >= arr.length - 1) {
      index = 0;
    }
    if (arr[index] > arr[index + 1]) {
      swap(index, index + 1);
    }
    index++;
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