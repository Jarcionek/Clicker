/*    */ package uk.co.jpawlak.clicker.gui;
/*    */ 
/*    */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberField
/*    */   extends JTextField
/*    */ {
/*    */   public NumberField(String text)
/*    */   {
/* 19 */     super(text);
/* 20 */     setPreferredSize(new Dimension(50, getPreferredSize().height));
/* 21 */     addKeyListener(new KeyAdapter()
/*    */     {
/*    */       public void keyTyped(KeyEvent e) {
/* 24 */         char c = e.getKeyChar();
/*    */         try {
/* 26 */           String text = NumberField.this.getText();
/* 27 */           if ((text != null) && (!text.equals(""))) {
/* 28 */             Integer.parseInt(text);
/*    */           }
/*    */         } catch (NumberFormatException ex) {
/* 31 */           e.consume();
/* 32 */           Toolkit.getDefaultToolkit().beep();
/* 33 */           return;
/*    */         }
/* 35 */         if (((c < '0') || (c > '9')) && (c != '\b') && (c != '')) {
/* 36 */           e.consume();
/* 37 */           Toolkit.getDefaultToolkit().beep();
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public NumberField() {
/* 44 */     this(null);
/*    */   }
/*    */   
/*    */   public long getLong() {
/* 48 */     return getText().length() == 0 ? 0L : Long.parseLong(getText());
/*    */   }
/*    */   
/*    */   public void paste() {}
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\NumberField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */