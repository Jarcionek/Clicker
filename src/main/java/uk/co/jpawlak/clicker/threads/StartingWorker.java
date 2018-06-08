/*    */ package uk.co.jpawlak.clicker.threads;
/*    */ 
/*    */

import clicker.gui.Status;
import clicker.gui.StatusLabel;

import javax.swing.*;

/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StartingWorker
/*    */   extends SwingWorker
/*    */ {
/*    */   private static final int WAIT_TIME = 3;
/*    */   private final StatusLabel statusLabel;
/*    */   
/*    */   public StartingWorker(StatusLabel statusLabel)
/*    */   {
/* 20 */     this.statusLabel = statusLabel;
/*    */   }
/*    */   
/*    */   protected Object doInBackground() throws Exception
/*    */   {
/* 25 */     Status status = Status.countdown(3);
/* 26 */     while ((Status.COUNTDOWN.getTime() >= 0) && (!isCancelled())) {
/* 27 */       this.statusLabel.setStatus(status);
/* 28 */       status.decrement();
/* 29 */       Thread.sleep(1000L);
/*    */     }
/* 31 */     this.statusLabel.setStatus(Status.ACTIVE);
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\threads\StartingWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */