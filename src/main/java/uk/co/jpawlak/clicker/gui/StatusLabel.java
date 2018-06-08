package uk.co.jpawlak.clicker.gui;



import javax.swing.*;
import java.awt.*;






public class StatusLabel
  extends JLabel
{
  public StatusLabel()
  {
    setStatus(Status.INACTIVE);
  }

  public void setStatus(Status status) {
    switch (status) {
    case ACTIVE:
      setText("Active");
      setForeground(Color.GREEN.darker());
      break;
    case INACTIVE:
      setText("Inactive");
      setForeground(Color.RED);
      break;
    case COUNTDOWN:
      setText("Starting in " + status.getTime() + "s");
      setForeground(Color.YELLOW.darker());
    }
  }
}


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\StatusLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */