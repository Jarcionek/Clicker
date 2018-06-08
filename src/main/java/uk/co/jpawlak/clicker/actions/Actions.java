/*    */ package uk.co.jpawlak.clicker.actions;
/*    */ 
/*    */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*    */
/*    */
/*    */
/*    */ 
/*    */ public class Actions implements Iterable<Action>
/*    */ {
/* 10 */   private List<Action> actions = new ArrayList();
/*    */   
/*    */   public void add(Action action) {
/* 13 */     this.actions.add(action);
/*    */   }
/*    */   
/*    */   public void execute() {
/* 17 */     for (Action action : this.actions) {
/* 18 */       action.execute();
/*    */     }
/*    */   }
/*    */   
/*    */   public Iterator<Action> iterator()
/*    */   {
/* 24 */     return this.actions.iterator();
/*    */   }
/*    */   
/*    */   public Action[] asArray() {
/* 28 */     return (Action[])this.actions.toArray(new Action[0]);
/*    */   }
/*    */   
/*    */   public void deleteActions(int[] selectedIndices)
/*    */   {
/* 33 */     Action[] array = asArray();
/* 34 */     for (int selectedIndice : selectedIndices) {
/* 35 */       array[selectedIndice] = null;
/*    */     }
/* 37 */     this.actions = new ArrayList(this.actions.size() - selectedIndices.length);
/* 38 */     for (Action action : array) {
/* 39 */       if (action != null) {
/* 40 */         this.actions.add(action);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public int[] moveUp(int[] selectedIndices) {
/* 46 */     Action[] array = (Action[])concatenate(new Action[] { null }, asArray());
/* 47 */     for (int i = 0; i < selectedIndices.length; i++) {
/* 48 */       swap(array, selectedIndices[i] + 1, selectedIndices[i]);
/* 49 */       selectedIndices[i] -= 1;
/*    */     }
/* 51 */     this.actions = new ArrayList(this.actions.size());
/* 52 */     for (Action action : array) {
/* 53 */       if (action != null) {
/* 54 */         this.actions.add(action);
/*    */       }
/*    */     }
/* 57 */     return selectedIndices;
/*    */   }
/*    */   
/*    */   public int[] moveDown(int[] selectedIndices) {
/* 61 */     Action[] array = (Action[])concatenate(asArray(), new Action[] { null });
/* 62 */     for (int i = selectedIndices.length - 1; i >= 0; i--) {
/* 63 */       swap(array, selectedIndices[i], selectedIndices[i] + 1);
/* 64 */       selectedIndices[i] += 1;
/*    */     }
/* 66 */     this.actions = new ArrayList(this.actions.size());
/* 67 */     for (Action action : array) {
/* 68 */       if (action != null) {
/* 69 */         this.actions.add(action);
/*    */       }
/*    */     }
/* 72 */     return selectedIndices;
/*    */   }
/*    */   
/*    */   public int[] copy(int[] selectedIndices) {
/* 76 */     Action[] array = asArray();
/* 77 */     for (int selectedIndice : selectedIndices) {
/* 78 */       this.actions.add(array[selectedIndice]);
/*    */     }
/* 80 */     int[] newIndices = new int[selectedIndices.length];
/* 81 */     for (int i = 0; i < newIndices.length; i++) {
/* 82 */       newIndices[i] = (array.length + i);
/*    */     }
/* 84 */     return newIndices;
/*    */   }
/*    */   
/*    */   private static void swap(Object[] array, int index, int index2)
/*    */   {
/* 89 */     Object t = array[index];
/* 90 */     array[index] = array[index2];
/* 91 */     array[index2] = t;
/*    */   }
/*    */   
/*    */   private static <T> T[] concatenate(T[] first, T[] second) {
/* 95 */     T[] result = Arrays.copyOf(first, first.length + second.length);
/* 96 */     System.arraycopy(second, 0, result, first.length, second.length);
/* 97 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\Actions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */