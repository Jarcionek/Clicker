package uk.co.jpawlak.clicker.gui;

import javax.swing.JComponent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TimeModule {

    private final NumberField hoursField;
    private final NumberField minutesField;
    private final NumberField secondsField;
    private final NumberField millisecondsField;

    public TimeModule() {
        this.hoursField = new NumberField("0");
        this.minutesField = new NumberField("0");
        this.secondsField = new NumberField("0");
        this.millisecondsField = new NumberField("0");
        FocusAdapter focusAdapter = new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                TimeModule.this.setMillis(TimeModule.this.getMillis()); // if someone enters 70m, it will change it into 1h10m
            }
        };
        this.hoursField.addFocusListener(focusAdapter);
        this.minutesField.addFocusListener(focusAdapter);
        this.secondsField.addFocusListener(focusAdapter);
        this.millisecondsField.addFocusListener(focusAdapter);
    }

    public JComponent getHoursField() {
        return this.hoursField;
    }

    public JComponent getMinutesField() {
        return this.minutesField;
    }

    public JComponent getSecondsField() {
        return this.secondsField;
    }

    public JComponent getMillisecondsField() {
        return this.millisecondsField;
    }

    public void setMillis(long time) {
        this.millisecondsField.setText(String.valueOf(time % 1000L));
        time /= 1000L;
        this.secondsField.setText(String.valueOf(time % 60L));
        time /= 60L;
        this.minutesField.setText(String.valueOf(time % 60L));
        time /= 60L;
        this.hoursField.setText(String.valueOf(time));
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public long getMillis() {
        long hours = this.hoursField.getLong();
        long minutes = hours * 60L + this.minutesField.getLong();
        long seconds = minutes * 60L + this.secondsField.getLong();
        long milliseconds = seconds * 1000L + this.millisecondsField.getLong();
        return milliseconds;
    }

    public void setEnabled(boolean enabled) {
        this.hoursField.setEnabled(enabled);
        this.minutesField.setEnabled(enabled);
        this.secondsField.setEnabled(enabled);
        this.millisecondsField.setEnabled(enabled);
    }

}
