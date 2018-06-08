package uk.co.jpawlak.clicker.actions;



import javax.swing.*;
import java.awt.*;









public class MouseButtonReleaseAction
  implements Action
{
  private final int buttons;
  private final String text;

  public static MouseButtonReleaseAction release(boolean left, boolean middle, boolean right)
  {
    return new MouseButtonReleaseAction(left, middle, right);
  }

  private MouseButtonReleaseAction(boolean left, boolean middle, boolean right) {
    int buttons = 0;
    String text = "";
    if (left) {
      buttons |= 0x400;
      text = text + "L";
    }
    if (middle) {
      buttons |= 0x800;
      text = text + "M";
    }
    if (right) {
      buttons |= 0x1000;
      text = text + "R";
    }
    if (buttons == 0) {
      throw new IllegalArgumentException();
    }
    this.buttons = buttons;
    this.text = text;
  }

  public void execute()
  {
    robot.mouseRelease(this.buttons);
  }

  public String toString()
  {
    return "Release buttons: " + this.text;
  }

  public static MouseButtonReleaseAction showPopup(Frame parentComponent) {
    JCheckBox left = new JCheckBox("Left");
    JCheckBox middle = new JCheckBox("Middle");
    JCheckBox right = new JCheckBox("Right");

    int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to release", left, middle, right), parentComponent
      .getTitle(), 2);

    if (choice == 0) {
      try {
        return new MouseButtonReleaseAction(left.isSelected(), middle.isSelected(), right.isSelected());
      } catch (IllegalArgumentException localIllegalArgumentException) {}
    }
    return null;
  }

  private static JPanel mouseButtonPanel(String text, JCheckBox left, JCheckBox middle, JCheckBox right) {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JLabel(text), "North");

    JPanel boxes = new JPanel(new GridLayout());
    boxes.add(left);
    boxes.add(middle);
    boxes.add(right);
    panel.add(boxes, "Center");

    return panel;
  }
}


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\MouseButtonReleaseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */