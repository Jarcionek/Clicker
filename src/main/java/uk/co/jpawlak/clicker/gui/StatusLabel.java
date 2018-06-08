/*    */ package uk.co.jpawlak.clicker.gui;
/*    */ 
/*    */

import javax.swing.*;
import java.awt.*;

/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StatusLabel
/*    */   extends JLabel
/*    */ {
/*    */   public StatusLabel()
/*    */   {
/* 14 */     setStatus(Status.INACTIVE);
/*    */   }
/*    */   
/*    */   public void setStatus(Status status) {
/* 18 */     switch (status) {
/*    */     case ACTIVE: 
/* 20 */       setText("Active");
/* 21 */       setForeground(Color.GREEN.darker());
/* 22 */       break;
/*    */     case INACTIVE: 
/* 24 */       setText("Inactive");
/* 25 */       setForeground(Color.RED);
/* 26 */       break;
/*    */     case COUNTDOWN: 
/* 28 */       setText("Starting in " + status.getTime() + "s");
/* 29 */       setForeground(Color.YELLOW.darker());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\StatusLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */