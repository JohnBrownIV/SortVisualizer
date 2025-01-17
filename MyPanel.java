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
//all (but Bogo)
int index;
//Insertion, Selection, Quick
int secondIndex;
int min;
boolean phase2;
//Quick only
int pivot;
int thirdIndex;
int quickPhase;

double barWidth;
double barHeight;
 
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
    phase2 = false;
    min = arr.length - 1;

    pivot = arr.length - 1;
    quickPhase = 0;
    thirdIndex = 0;

    this.setPreferredSize(new Dimension(1920,1080));
    this.setBackground(Color.white);

    //figuring out dimensions
    barHeight = (960 / (double)arrLength);
    barWidth = (1800 / (double)arrLength);

  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.clearRect(0, 0, 1920, 1080);

    g2D.setColor(Color.black);
    g2D.fillRect(50, 980, 1800, 5);

    for (int x = 0; x < arr.length; x++) {
      if (x == index && sortType != 4) {
        g2D.setColor(Color.orange);
      } else if ((sortType == 2 || sortType == 3) && x == secondIndex) {
        g2D.setColor(Color.green);
      } else if (sortType == 2  && x == min) {
        g2D.setColor(Color.red);
      } else if (sortType == 4) {//Quicksort
        if (x == pivot) {
          g2D.setColor(Color.red);
        } else if (x == index) {
          g2D.setColor(Color.green);
        } else if (x == secondIndex) {
          g2D.setColor(Color.orange);
        } else if (x == thirdIndex) {
          g2D.setColor(Color.yellow);
        } else {
          g2D.setColor(Color.black);
        }
      } else {
        g2D.setColor(Color.black);
      }
     g2D.fillRect(50 + (int)(x * barWidth), 980 - (int)(arr[x] * barHeight), (int)barWidth, 1000);
    }

  }
  //NEW FRAME
  @Override
	public void actionPerformed(ActionEvent e) {
    if (countdown == 0) {
      if (checkSort()) {
        if (!done) {
          System.out.println("Good job! Ya did it!");
          index = -1;
          min = -1;
          secondIndex = -1;
          repaint();
          done = true;
        }
      } else {
        if (sortType == 1) {//Bubble
          bubSort();
        } else if (sortType == 2) {
          selectSort();
        } else if (sortType == 3) {
          insertSort();
        } else if (sortType == 4) {
          quickSort();
        } else if (sortType == 5) {//bogo
          shuffle();
        }
        //System.out.println(Arrays.toString(arr));
        repaint();
      }
    } else {
      countdown--;
    }
  }
  private void quickSort() {
    if (quickPhase == 0) {//Assign pivot
      pivot = ((pivot - index) / 2) + index;
      if (pivot != index) {//If the pivot hasn't hit the side
        secondIndex = index;
        thirdIndex = arr.length - 1;
        quickPhase = 1;
      } else {
        ++index;
      }
    } else if (quickPhase == 1) {//left half of partition
      if (arr[secondIndex] > arr[pivot]) {//We need to make a swap
        if (thirdIndex > pivot) {//The right half has a smaller element
          quickPhase = 2;
        } else {
          swap(pivot, pivot - 1);
          --pivot;
        }
      } else {
        secondIndex++;
        if (secondIndex > pivot) {
          quickPhase = 0;
        }
      }
    } else if (quickPhase == 2) {//Find a right-side swap candidate
      if (arr[thirdIndex] < arr[pivot]) {//This index needs to be swapped
        swap(thirdIndex, secondIndex);
        quickPhase = 1;
      } else {
        --thirdIndex;
        if (thirdIndex == pivot) {
          System.out.println("HIT PIVOT, R");
          quickPhase = 2;
        }
      }
    }
  }
  private void insertSort() {
    if (index == 0) {
      index = 1;
    }
    if (!phase2) {
      if (arr[secondIndex] < arr[index]) {
        secondIndex++;
      } else {
        insertShift();
        phase2 = true;
      }
    } else {
      index++;
      secondIndex = 0;
      phase2 = false;
    }
  }
  private void insertShift() {
    int temp = arr[index];
    for (int i = index; i > secondIndex; --i) {
      arr[i] = arr[i - 1];
    }
    arr[secondIndex] = temp;
  }
  private void selectSort() {
    if (index >= arr.length - 1) {
      index = 0;
    }
    if (secondIndex >= arr.length - 1) {
      phase2 = true;
    }
    if (!phase2) {
      if (arr[secondIndex] < arr[min]) {
        min = secondIndex;
      }
      secondIndex++;
    } else if (phase2) {
      swap(index, min);
      min = arr.length - 1;
      index++;
      secondIndex = index;
      phase2 = false;
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