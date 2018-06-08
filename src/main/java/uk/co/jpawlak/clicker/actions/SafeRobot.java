/*    */ package uk.co.jpawlak.clicker.actions;
/*    */ 
/*    */

import java.awt.*;

/*    */
/*    */ 
/*    */ class SafeRobot extends Robot
/*    */ {
/*    */   public static SafeRobot robotOrNull()
/*    */   {
/*    */     try
/*    */     {
/* 12 */       return new SafeRobot();
/*    */     } catch (AWTException e) {
/* 14 */       e.printStackTrace(); }
/* 15 */     return null;
/*    */   }
/*    */   
/*    */   private SafeRobot()
/*    */     throws AWTException
/*    */   {}
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\SafeRobot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */