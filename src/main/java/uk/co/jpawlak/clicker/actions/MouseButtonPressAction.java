package uk.co.jpawlak.clicker.actions;



import javax.swing.*;
import java.awt.*;









public class MouseButtonPressAction
  implements Action
{
  private final int buttons;
  private final String text;

  public static MouseButtonPressAction press(boolean left, boolean middle, boolean right)
  {
    return new MouseButtonPressAction(left, middle, right);
  }

  private MouseButtonPressAction(boolean left, boolean middle, boolean right) {
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
    robot.mousePress(this.buttons);
  }

  public String toString()
  {
    return "Press buttons: " + this.text;
  }

  public static MouseButtonPressAction showPopup(Frame parentComponent) {
    JCheckBox left = new JCheckBox("Left");
    JCheckBox middle = new JCheckBox("Middle");
    JCheckBox right = new JCheckBox("Right");

    int choice = JOptionPane.showConfirmDialog(parentComponent, mouseButtonPanel("Choose buttons to press", left, middle, right), parentComponent
      .getTitle(), 2);

    if (choice == 0) {
      try {
        return new MouseButtonPressAction(left.isSelected(), middle.isSelected(), right.isSelected());
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


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\MouseButtonPressAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */