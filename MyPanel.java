import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {

Timer timer;
int[] trueArr;
int sortType;
ArrayList<ArrayState> arrayStates;
int countdown;
boolean ranSort;

double barWidth;
double barHeight;
 
  MyPanel(int arrLength, int sort) {

    trueArr = new int[arrLength];
    for (int i = 0; i < arrLength; i++) {
      trueArr[i] = i;
    }
    mixUp();
    System.out.println("Starting Array: " + Arrays.toString(trueArr));

    timer = new Timer(15, this);
	  timer.start();
    countdown = 200;

    sortType = sort;
    ranSort = false;

    arrayStates = new ArrayList<ArrayState>();
    arrayStates.add(new ArrayState(trueArr));

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
    //g2D.fillRect(50, 980, 1800, 5);

    ArrayState inArr = arrayStates.get(0);
    for (int x = 0; x < inArr.arr.length; x++) {
      switch(sortType) {
        case 2:
          if (x == inArr.index2) {
            g2D.setColor(Color.blue);
            break;
          }
        case 1:
          if (x == inArr.index) {
            g2D.setColor(Color.green);
            break;
          }
        default:
          g2D.setColor(Color.black);
      }
      g2D.fillRect(50 + (int)(x * barWidth), 980 - (int)(inArr.arr[x] * barHeight), (int)barWidth, 1000);
    }
    if (arrayStates.size() > 1) {
      arrayStates.remove(0);
    }
  }
  //NEW FRAME
  @Override
	public void actionPerformed(ActionEvent e) {
    if (ranSort != true) {
      if (countdown == 0) {
        switch (sortType) {
          case 1:
          trueArr = bubbleSort(trueArr);
          System.out.println("ran bs");
          break;
          case 2:
          trueArr = insertionSort(trueArr);
          System.out.println("ran is");
          break;
        }
        ranSort = true;
      } else {
        countdown--;
      }
    }
    if (arrayStates.size() > 1) {
      repaint();
    }
  }
  private void quickSort() {
    
  }
  private int[] insertionSort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      //finding the correct spot on the sub array
      int slot = 0;
      for (int y = 0; y < i; ++y) {
        arrayStates.add(new ArrayState(arr, i, y));//adding it before we do everything else
        if (arr[y] > arr[i]) {
          slot = y;
          break;
        }
      }
      //shifting slot and on into the index
      int temp = arr[i];
      for (int y = slot; y < i; ++y) {
        arr[y] = arr[y + 1];
      }
      arr[slot] = temp;
    }
    return arr;
  }
  private void selectSort() {
    
  }
  private int[] bubbleSort(int[] arr) {
    int length = arr.length;
    int temp;
    boolean changed = true;
    int timesRan = 0;
    while (changed) {
      changed = false;
      for (int i = 1; i < length - timesRan; i++) {
        if (arr[i] < arr[i - 1]) {
          temp = arr[i - 1];
          arr[i - 1] = arr[i];
          arr[i] = temp;
          changed = true;
          /*for (int x = 0; x < arr.length; x++) {
          System.out.print(arr[x] + " ");
          }
          System.out.println();*/ //This prints it as it goes 'cause it looks cool
        }
        arrayStates.add(new ArrayState(arr, i));
      }
      ++timesRan;
    }
    return arr;
  }

  private void mixUp() {
    shuffle();
    //Reverse
    for (int i = 0; i < trueArr.length / 2; i++) {
      swap(i, (trueArr.length - 1) - i);
    }
    shuffle();
    //Reverse
    for (int i = 0; i < trueArr.length / 2; i++) {
      swap(i, (trueArr.length - 1) - i);
    }
    shuffle();
  }
  public void shuffle() {
    Random random = new Random();
    int count = trueArr.length;
    for (int i = count; i > 1; i--) {
        swap(i - 1, random.nextInt(i));
    }
  }
  private void swap(int i, int j) {
      int temp = trueArr[i];
      trueArr[i] = trueArr[j];
      trueArr[j] = temp;
  }
}
class ArrayState {
  int[] arr;
  int index = -1;
  int index2 = -1;
  int index3 = -1;
  ArrayState(int[] inArr) {
    arr = Arrays.copyOf(inArr, inArr.length - 1);
  }
  ArrayState(int[] inArr, int inIndex) {
    arr = Arrays.copyOf(inArr, inArr.length - 1);
    index = inIndex;
  }
  ArrayState(int[] inArr, int inIndex, int inIndex2) {
    arr = Arrays.copyOf(inArr, inArr.length - 1);
    index = inIndex;
    index2 = inIndex2;
  }
  ArrayState(int[] inArr, int inIndex, int inIndex2, int inIndex3) {
    arr = Arrays.copyOf(inArr, inArr.length - 1);
    index = inIndex;
    index2 = inIndex2;
    index3 = inIndex3;
  }
}