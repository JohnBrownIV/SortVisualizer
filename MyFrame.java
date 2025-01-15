import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyFrame extends JFrame implements KeyListener{
 
 MyPanel panel;
 
 MyFrame(int arrLength, int sort){
  
  panel = new MyPanel(arrLength, sort);
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  this.add(panel);
  this.pack();
  this.setLocationRelativeTo(null);
  this.addKeyListener(this);
  this.setVisible(true);
  this.toFront();
  this.requestFocus();
  
 }
 @Override
	public void keyTyped(KeyEvent e) {
  }
 @Override
	public void keyPressed(KeyEvent e) {
  }
  @Override
	public void keyReleased(KeyEvent e) {
  }
}