package uk.co.jpawlak.clicker.gui;

import javax.swing.JLabel;
import java.awt.Color;

public class StatusLabel extends JLabel {

    public StatusLabel() {
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
