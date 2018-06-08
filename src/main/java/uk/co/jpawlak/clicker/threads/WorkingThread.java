/*    */ package uk.co.jpawlak.clicker.threads;
/*    */ 
/*    */

import clicker.actions.Actions;
import clicker.gui.TimeModule;

import java.util.LinkedList;
import java.util.List;

/*    */
/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorkingThread
/*    */   extends Thread
/*    */ {
/*    */   private final TimeModule timeModule;
/*    */   private final long original;
/*    */   private final List<ThreadListener> listeners;
/*    */   private final Actions actions;
/*    */   
/*    */   public static WorkingThread workingThread(TimeModule timeModule, Actions actions)
/*    */   {
/* 20 */     return new WorkingThread(timeModule, actions);
/*    */   }
/*    */   
/*    */   private WorkingThread(TimeModule timeModule, Actions actions) {
/* 24 */     this.timeModule = timeModule;
/* 25 */     this.actions = actions;
/* 26 */     this.original = timeModule.getMillis();
/* 27 */     this.listeners = new LinkedList();
/*    */   }
/*    */   
/*    */   public void run()
/*    */   {
/* 32 */     long end = now() + this.timeModule.getMillis();
/* 33 */     while ((now() < end) && (!isInterrupted())) {
/* 34 */       this.timeModule.setMillis(end - now());
/* 35 */       this.actions.execute();
/*    */     }
/* 37 */     this.timeModule.setMillis(this.original);
/* 38 */     for (ThreadListener listener : this.listeners) {
/* 39 */       listener.finished();
/*    */     }
/*    */   }
/*    */   
/*    */   public void addThreadListener(ThreadListener threadListener) {
/* 44 */     this.listeners.add(threadListener);
/*    */   }
/*    */   
/*    */   private static long now() {
/* 48 */     return System.currentTimeMillis();
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\threads\WorkingThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */