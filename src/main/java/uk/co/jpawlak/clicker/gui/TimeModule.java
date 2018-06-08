/*    */ package uk.co.jpawlak.clicker.gui;
/*    */ 
/*    */

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeModule
/*    */ {
/*    */   private final NumberField hoursField;
/*    */   private final NumberField minutesField;
/*    */   private final NumberField secondsField;
/*    */   private final NumberField millisecondsField;
/*    */   
/*    */   public TimeModule()
/*    */   {
/* 18 */     this.hoursField = new NumberField("0");
/* 19 */     this.minutesField = new NumberField("0");
/* 20 */     this.secondsField = new NumberField("0");
/* 21 */     this.millisecondsField = new NumberField("0");
/* 22 */     FocusAdapter focusAdapter = new FocusAdapter()
/*    */     {
/*    */       public void focusLost(FocusEvent e) {
/* 25 */         TimeModule.this.setMillis(TimeModule.this.getMillis());
/*    */       }
/* 27 */     };
/* 28 */     this.hoursField.addFocusListener(focusAdapter);
/* 29 */     this.minutesField.addFocusListener(focusAdapter);
/* 30 */     this.secondsField.addFocusListener(focusAdapter);
/* 31 */     this.millisecondsField.addFocusListener(focusAdapter);
/*    */   }
/*    */   
/*    */   public JComponent getHoursField() {
/* 35 */     return this.hoursField;
/*    */   }
/*    */   
/*    */   public JComponent getMinutesField() {
/* 39 */     return this.minutesField;
/*    */   }
/*    */   
/*    */   public JComponent getSecondsField() {
/* 43 */     return this.secondsField;
/*    */   }
/*    */   
/*    */   public JComponent getMillisecondsField() {
/* 47 */     return this.millisecondsField;
/*    */   }
/*    */   
/*    */   public void setMillis(long time) {
/* 51 */     this.millisecondsField.setText(String.valueOf(time % 1000L));
/* 52 */     time /= 1000L;
/* 53 */     this.secondsField.setText(String.valueOf(time % 60L));
/* 54 */     time /= 60L;
/* 55 */     this.minutesField.setText(String.valueOf(time % 60L));
/* 56 */     time /= 60L;
/* 57 */     this.hoursField.setText(String.valueOf(time));
/*    */   }
/*    */   
/*    */   public long getMillis() {
/* 61 */     long hours = this.hoursField.getLong();
/* 62 */     long minutes = hours * 60L + this.minutesField.getLong();
/* 63 */     long seconds = minutes * 60L + this.secondsField.getLong();
/* 64 */     long milliseconds = seconds * 1000L + this.millisecondsField.getLong();
/* 65 */     return milliseconds;
/*    */   }
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 69 */     this.hoursField.setEnabled(enabled);
/* 70 */     this.minutesField.setEnabled(enabled);
/* 71 */     this.secondsField.setEnabled(enabled);
/* 72 */     this.millisecondsField.setEnabled(enabled);
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\TimeModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */