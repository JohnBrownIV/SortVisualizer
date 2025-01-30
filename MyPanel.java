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
boolean nextSort;

double barWidth;
double barHeight;
 
  MyPanel(int arrLength, int sort) {

    arrayStates = new ArrayList<ArrayState>();
    makeArr(arrLength);

    timer = new Timer(15, this);
	  timer.start();
    countdown = 200;

    sortType = sort;
    ranSort = false;
    nextSort = false;

    this.setPreferredSize(new Dimension(1920,1080));
    this.setBackground(Color.black);


  }

  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    g2D.setColor(Color.black);
    g2D.fillRect(0, 0, 1920, 1080);
    g2D.setFont(new Font("Times New Roman", 1, 50));
    g2D.setColor(Color.white);
    //g2D.fillRect(50, 980, 1800, 5);
    //Draw name
    switch (sortType) {
      case 1:
        g2D.drawString("Bubble Sort", 10, 55);
        break;
      case 2:
        g2D.drawString("Insertion Sort", 10, 55);
        break;
      case 3:
        g2D.drawString("Selection Sort", 10, 55);
        break;
      case 4:
        g2D.drawString("Merge Sort", 10, 55);
        break;
      case 5:
        g2D.drawString("Bogo Sort", 10, 55);
        break;
      case 6:
        g2D.drawString("Quick Sort", 10, 55);
        break;
      case 7:
        g2D.drawString("Shaker Sort", 10, 55);
        break; 
    }
    //Draw number of items
    g2D.drawString("Items: " + trueArr.length, 10, 105);
    ArrayState inArr = arrayStates.get(0);
    for (int x = 0; x < inArr.arr.length; ++x) {
      g2D.setColor(Color.white);
      switch(sortType) {
        case 7:
        case 6:
          if (x == inArr.index4) {
            g2D.setColor(Color.orange);
          }
        case 4:
        case 3:
          if (x == inArr.index3) {
            g2D.setColor(Color.green);
          }
        case 2:
          if (x == inArr.index2) {
            g2D.setColor(Color.blue);
          }
        case 1:
          if (x == inArr.index) {
            g2D.setColor(Color.red);
          }
      }
      g2D.fillRect(50 + (int)(x * barWidth), 980 - (int)(inArr.arr[x] * barHeight), (int)barWidth, 1000);
      g2D.setColor(Color.black);
      g2D.drawRect(50 + (int)(x * barWidth), 980 - (int)(inArr.arr[x] * barHeight), (int)barWidth, 1000);
      //g2D.drawString("" + inArr.arr[x], 50 + (int)(x * barWidth), 980 - (int)(inArr.arr[x] * barHeight));//show values
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
          case 1://bubble
            trueArr = bubbleSort(trueArr);
            System.out.println("ran bs");
            break;
          case 2://insert
            trueArr = insertionSort(trueArr);
            System.out.println("ran is");
            break;
          case 3://select
            trueArr = selectSort(trueArr);
            System.out.println("ran ss");
            break;
          case 4://Merge
            trueArr = mergeSort(trueArr);
            System.out.println("ran ms");
            System.out.println(Arrays.toString(trueArr));
            break;
          case 5://Bogo
            trueArr = bogoSort(trueArr);
            System.out.println("ran bs");
            break;
          case 6://Quick
            trueArr = quickSort(trueArr);
            System.out.println("ran qs");
            break;
          case 7://Shaker
            trueArr = shakerSort(trueArr);
            System.out.println("ran ShakeS");
            break;
        }
        ranSort = true;
        arrayStates.add(new ArrayState(trueArr));
        countdown = 200;
      } else {
        countdown--;
      }
    } else if (nextSort) {
      if (countdown == 0) {
        ranSort = false;
        nextSort = false;
        sortType = rand(1, 8);
        switch (sortType) {
          case 1:
            makeArr(75);
            break;
          case 2:
            makeArr(150);
            break;
          case 3:
            makeArr(150);
            break;
          case 4:
            makeArr(500);
            break;
          case 5:
            makeArr(20);
            break;
          case 6:
            makeArr(500);
            break;
          case 7:
            makeArr(100);
            break;
        }
      } else {
        --countdown;
        System.out.println("Nextsort countdown down");
      }
    }
    repaint();
    if (arrayStates.size() <= 1 && countdown == 200) {
      nextSort = true;
    }
    
  }
  private int[] quickSort(int[] arr) {
    return quickSort(arr, 0, arr.length);
  }
  private int[] quickSort(int[] arr, int lowBound, int upBound) {
    int length = upBound - lowBound;
    if (length <= 1) {
      return arr;
    }
    //Partition the array
    int pivot = upBound - 1;//pivot is the leftmost element
    for (int i = pivot; i >= lowBound; --i) {
      arrayStates.add(new ArrayState(arr, pivot, lowBound, upBound, i));
      if (arr[i] > arr[pivot]) {//Should be on the right of the pivot
        arr = swap(arr, pivot - 1, i);
        arr = swap(arr, pivot, pivot - 1);
        --pivot;
        continue;
      }
    }
    //Run quicksort on the lower portions of the array
    arr = quickSort(arr, lowBound, pivot);
    arr = quickSort(arr, pivot, upBound);
    return arr;
  }
  private int[] mergeSort(int[] arr) {
    return mergeSort(arr, 0, arr.length);
  }
  private int[] mergeSort(int[] arr, int lowBound, int upBound) {
    arrayStates.add(new ArrayState(arr,-1, lowBound, upBound));
    int length = upBound - lowBound;
    if (length <= 1) {
      return arr;
    }
    //splitting
    //Create merge sorted sublists
    int split = (length / 2) + lowBound;
    mergeSort(arr, lowBound, split);
    mergeSort(arr, split, upBound);
    //Merge those sublists
    int sub1index = lowBound;
    int sub2index = split;
    int[] fuseArr = new int[length];
    for (int i = 0; i < length; ++i) {//loop through the bounds of the array
      arrayStates.add(new ArrayState(arr,i + lowBound, lowBound, upBound));
      //figure out how the hell we're merging
      if (sub1index == split) {//sub1 is at its end;
        fuseArr[i] = arr[sub2index];
        ++sub2index;
        continue;
      }
      if (sub2index == upBound) {//sub2 is at its end
        fuseArr[i] = arr[sub1index];
        ++sub1index;
        continue;
      }
      if (arr[sub1index] < arr[sub2index]) {
        fuseArr[i] = arr[sub1index];
        ++sub1index;
      } else {
        fuseArr[i] = arr[sub2index];
        ++sub2index;
      }
    }
    //Now add the fused array onto the original array
    for (int i = lowBound; i < upBound; ++i) {
      arr[i] = fuseArr[i - lowBound];
    }
    return arr;
  }
  private int[] insertionSort(int[] arr) {
    //looping through the array
    for (int index = 1; index < arr.length; ++index) {
      //finding the position in our sorting array where we want to put our index
      int pos = index;//the position we'll switch our index with
      for (int y = 0; y <= index; ++y) {
        arrayStates.add(new ArrayState(arr, index, y));
        if (arr[y] > arr[index]) {
          pos = y;
          break;
        }
      }
      //Now we need to shift the (sorted)array over to make space for the new index position
      int temp = arr[index];
      for (int y = index; y > pos; --y) {
        arr[y] = arr[y - 1];
      }
      arr[pos] = temp;
      arrayStates.add(new ArrayState(arr, index, pos));
    }
    return arr;
  }
  private int[] selectSort(int[] arr) {
    for (int i = 0; i < arr.length; ++i) {//loop through the array
      int min = i;
      for (int y = i; y < arr.length; ++y) {//loop through the rest of the array to locate the minimum
        arrayStates.add(new ArrayState(arr, i, y, min));
        if (arr[y] < arr[min]) {
          min = y;
        }
      }
      arr = swap(arr,min,i);//swap them
    }
    return arr;
  }
  private int[] bogoSort(int[] arr) {
    for (int i = 0; i < arr.length * arr.length; ++i) {
      arr = mixUp(arr);
      arrayStates.add(new ArrayState(arr));
      if (isSorted(arr)) {
        return arr;
      }
    }
    return arr;
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
  private int[] shakerSort(int[] arr) {
    boolean left = false;
    int lowBound = 0;
    int upBound = arr.length - 1;
    while (upBound != lowBound && isSorted(arr) == false) {
      if (left) {
        for (int i = upBound; i > lowBound; --i) {
          arrayStates.add(new ArrayState(arr, i, upBound, lowBound));
          if (arr[i] < arr[i - 1]) {//swap i with right element
            arr = swap(arr, i, i - 1);
          }
        }
        ++lowBound;
        left = false;
      } else {//going right
        for (int i = lowBound; i < upBound; ++i) {
          arrayStates.add(new ArrayState(arr, i, upBound, lowBound));
          if (arr[i] > arr[i + 1]) {//swap i with right element
            arr = swap(arr, i, i + 1);
          }
        }
        --upBound;
        left = true;
      }
    }
    return arr;
  }

  private int[] mixUp(int[] arr) {
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
  public int[] shuffle(int[] arr) {
    Random random = new Random();
    int count = trueArr.length;
    for (int i = count; i > 1; i--) {
      arr = swap(arr, i - 1, random.nextInt(i));
    }
    return arr;
  }
  private int[] swap(int[] arr, int i, int j) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
      return arr;
  }
  private boolean isSorted(int[] arr) {
    for (int i = 0; i < arr.length - 1; ++i) {
      if (arr[i] > arr[i + 1]) {
        return false;
      }
    }
    return true;
  }
  public int rand(int min, int max) {
    Random rand = new Random();
    return rand.nextInt(max - min) + min;
  }
  private void makeArr(int size) {
    trueArr = new int[size];
    for (int i = 0; i < size; i++) {
      trueArr[i] = i;
    }
    trueArr = mixUp(trueArr);
    System.out.println("Starting Array: " + Arrays.toString(trueArr));
    arrayStates.clear();
    arrayStates.add(new ArrayState(trueArr));

    //find bounds
    barHeight = (960 / (double)size);
    barWidth = (1800 / (double)size);
  }
}
class ArrayState {
  int[] arr;
  int index = -1;
  int index2 = -1;
  int index3 = -1;
  int index4 = -1;
  ArrayState(int[] inArr) {
    arr = Arrays.copyOf(inArr, inArr.length);
  }
  ArrayState(int[] inArr, int inIndex) {
    arr = Arrays.copyOf(inArr, inArr.length);
    index = inIndex;
  }
  ArrayState(int[] inArr, int inIndex, int inIndex2) {
    arr = Arrays.copyOf(inArr, inArr.length);
    index = inIndex;
    index2 = inIndex2;
  }
  ArrayState(int[] inArr, int inIndex, int inIndex2, int inIndex3) {
    arr = Arrays.copyOf(inArr, inArr.length);
    index = inIndex;
    index2 = inIndex2;
    index3 = inIndex3;
  }
  ArrayState(int[] inArr, int inIndex, int inIndex2, int inIndex3, int inIndex4) {
    arr = Arrays.copyOf(inArr, inArr.length);
    index = inIndex;
    index2 = inIndex2;
    index3 = inIndex3;
    index4 = inIndex4;
  }
}