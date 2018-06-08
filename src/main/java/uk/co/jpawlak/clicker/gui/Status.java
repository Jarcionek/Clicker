/*    */ package uk.co.jpawlak.clicker.gui;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Status
/*    */ {
/*  8 */   ACTIVE(0), 
/*  9 */   INACTIVE(0), 
/* 10 */   COUNTDOWN(3);
/*    */   
/*    */   private int time;
/*    */   
/*    */   private Status(int time) {
/* 15 */     this.time = time;
/*    */   }
/*    */   
/*    */   public static Status countdown(int time) {
/* 19 */     COUNTDOWN.time = time;
/* 20 */     return COUNTDOWN;
/*    */   }
/*    */   
/*    */   public int getTime() {
/* 24 */     return this.time;
/*    */   }
/*    */   
/*    */   public Status decrement() {
/* 28 */     this.time -= 1;
/* 29 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\Status.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */