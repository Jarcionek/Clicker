/*    */ package uk.co.jpawlak.clicker.actions;
/*    */ 
/*    */

import javax.swing.*;
import java.awt.*;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ public class MouseButtonPressAction
/*    */   implements Action
/*    */ {
/*    */   private final int buttons;
/*    */   private final String text;
/*    */   
/*    */   public static MouseButtonPressAction press(boolean left, boolean middle, boolean right)
/*    */   {
/* 20 */     return new MouseButtonPressAction(left, middle, right);
/*    */   }
/*    */   
/*    */   private MouseButtonPressAction(boolean left, boolean middle, boolean right) {
/* 24 */     int buttons = 0;
/* 25 */     String text = "";
/* 26 */     if (left) {
/* 27 */       buttons |= 0x400;
/* 28 */       text = text + "L";
/*    */     }
/* 30 */     if (middle) {
/* 31 */       buttons |= 0x800;
/* 32 */       text = text + "M";
/*    */     }
/* 34 */     if (right) {
/* 35 */       buttons |= 0x1000;
/* 36 */       text = text + "R";
/*    */     }
/* 38 */     if (buttons == 0) {
/* 39 */       throw new IllegalArgumentException();
/*    */     }
/* 41 */     this.buttons = buttons;
/* 42 */     this.text = text;
/*    */   }
/*    */   
/*    */   public void execute()
/*    */   {
/* 47 */     robot.mousePress(this.buttons);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 52 */     return "Press buttons: " + this.text;
/*    */   }
/*    */   
/*    */   public static MouseButtonPressAction showPopup(Frame parentComponent) {
/* 56 */     JCheckBox left = new JCheckBox("Left");
/* 57 */     JCheckBox middle = new JCheckBox("Middle");
/* 58 */     JCheckBox right = new JCheckBox("Right");
/*    */     
/* 60 */     int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to press", left, middle, right), parentComponent
/* 61 */       .getTitle(), 2);
/*    */     
/* 63 */     if (choice == 0) {
/*    */       try {
/* 65 */         return new MouseButtonPressAction(left.isSelected(), middle.isSelected(), right.isSelected());
/*    */       } catch (IllegalArgumentException localIllegalArgumentException) {}
/*    */     }
/* 68 */     return null;
/*    */   }
/*    */   
/*    */   private static JPanel mouseButtonPanel(String text, JCheckBox left, JCheckBox middle, JCheckBox right) {
/* 72 */     JPanel panel = new JPanel(new BorderLayout());
/* 73 */     panel.add(new JLabel(text), "North");
/*    */     
/* 75 */     JPanel boxes = new JPanel(new GridLayout());
/* 76 */     boxes.add(left);
/* 77 */     boxes.add(middle);
/* 78 */     boxes.add(right);
/* 79 */     panel.add(boxes, "Center");
/*    */     
/* 81 */     return panel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\MouseButtonPressAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */