package uk.co.jpawlak.clicker.actions;



import javax.swing.*;
import java.awt.*;







public class SleepAction
  implements Action
{
  private final long time;

  public static SleepAction sleep(long time)
  {
    return new SleepAction(time);
  }

  private SleepAction(long time) {
    this.time = time;
  }

  public void execute()
  {
    try {
      Thread.sleep(this.time);
    }
    catch (InterruptedException localInterruptedException) {}
  }

  public String toString() {
    return "Sleep: " + this.time + "ms";
  }

  public static SleepAction showPopup(Frame parentComponent) {
    String input;
    do {
      input = JOptionPane.showInputDialog(parentComponent, "Enter wait time (milliseconds)", parentComponent.getTitle(), -1);
    } while ((input != null) && (!isLong(input)));
    return input == null ? null : new SleepAction(Long.parseLong(input));
  }

  private static boolean isLong(String value) {
    try {
      Long.parseLong(value);
    } catch (NumberFormatException ex) {
      return false;
    }
    return true;
  }
}


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\SleepAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */