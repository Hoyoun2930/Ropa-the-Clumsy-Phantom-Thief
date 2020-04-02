package ui.container;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import ui.UiButton;
import ui.UiMenu;

public abstract class MyContainer extends Container {
	  static final int width = 1280;
	  
	  static final int height = 720;
	  
	  private int rx;
	  
	  private int ry;
	  
	  protected ArrayList<UiButton> buttons;
	  
	  protected ArrayList<UiMenu> menus;
	  
	  protected JFrame parent;
	  
	  public MyContainer(JFrame parent) {
	    setSize(1280, 720);
	    this.parent = parent;
	    this.buttons = new ArrayList<>();
	    this.menus = new ArrayList<>();
	  }
	  
	  public void setRxy(int x, int y) {
	    this.rx = x;
	    this.ry = y;
	  }
	  
	  public void addButton(UiButton button) {
	    this.buttons.add(button);
	  }
	  
	  public void setMenu(int start, int end) {
	    UiMenu menu = new UiMenu();
	    for (int i = start; i <= end; i++)
	      menu.addItem(this.buttons.get(i)); 
	    this.menus.add(menu);
	  }
	  
	  public void setListenr(JFrame mainf) {
	    int i;
	    for (i = 0; i < this.buttons.size(); i++) {
	      mainf.addMouseMotionListener((MouseMotionListener)this.buttons.get(i));
	      mainf.addMouseListener((MouseListener)this.buttons.get(i));
	      ((UiButton)this.buttons.get(i)).setStatus(0);
	    } 
	    for (i = 0; i < this.menus.size(); i++)
	      mainf.addKeyListener((KeyListener)this.menus.get(i)); 
	  }
	  
	  public void removeListenr(JFrame mainf) {
	    int i;
	    for (i = 0; i < this.buttons.size(); i++) {
	      mainf.removeMouseListener((MouseListener)this.buttons.get(i));
	      mainf.removeMouseListener((MouseListener)this.buttons.get(i));
	      ((UiButton)this.buttons.get(i)).setStatus(0);
	    } 
	    for (i = 0; i < this.menus.size(); i++)
	      mainf.removeKeyListener((KeyListener)this.menus.get(i)); 
	  }
	  
	  public void paint(Graphics graph) {
	    for (int i = 0; i < this.buttons.size(); i++) {
	      ((UiButton)this.buttons.get(i)).setGraphic(graph);
	      ((UiButton)this.buttons.get(i)).paint();
	    } 
	  }
	  
	  public abstract int running();
	}