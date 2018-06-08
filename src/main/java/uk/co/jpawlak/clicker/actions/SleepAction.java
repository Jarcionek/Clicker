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
/*    */ public class SleepAction
/*    */   implements Action
/*    */ {
/*    */   private final long time;
/*    */   
/*    */   public static SleepAction sleep(long time)
/*    */   {
/* 17 */     return new SleepAction(time);
/*    */   }
/*    */   
/*    */   private SleepAction(long time) {
/* 21 */     this.time = time;
/*    */   }
/*    */   
/*    */   public void execute()
/*    */   {
/*    */     try {
/* 27 */       Thread.sleep(this.time);
/*    */     }
/*    */     catch (InterruptedException localInterruptedException) {}
/*    */   }
/*    */   
/*    */   public String toString() {
/* 33 */     return "Sleep: " + this.time + "ms";
/*    */   }
/*    */   
/*    */   public static SleepAction showPopup(Frame parentComponent) {
/*    */     String input;
/*    */     do {
/* 39 */       input = JOptionPane.showInputDialog(parentComponent, "Enter wait time (milliseconds)", parentComponent.getTitle(), -1);
/* 40 */     } while ((input != null) && (!isLong(input)));
/* 41 */     return input == null ? null : new SleepAction(Long.parseLong(input));
/*    */   }
/*    */   
/*    */   private static boolean isLong(String value) {
/*    */     try {
/* 46 */       Long.parseLong(value);
/*    */     } catch (NumberFormatException ex) {
/* 48 */       return false;
/*    */     }
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\SleepAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */