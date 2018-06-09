package uk.co.jpawlak.clicker.gui;

import uk.co.jpawlak.clicker.threads.StartingWorker;
import uk.co.jpawlak.clicker.threads.ThreadListener;
import uk.co.jpawlak.clicker.threads.WorkingThread;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;

public class MainWindow extends JFrame {

    private final TimeModule timeModule;
    private final StatusLabel statusLabel;
    private final JButton startButton;
    private final JButton stopButton;
    private final ActionsManager actionsManager;
    private StartingWorker starting;
    private WorkingThread working;

    public MainWindow() {
        setTitle("Clicker");

        this.timeModule = new TimeModule();
        this.statusLabel = new StatusLabel();
        this.startButton = new JButton("Start");
        this.stopButton = new JButton("Stop");
        this.actionsManager = new ActionsManager(this);

        this.starting = new StartingWorker(null);
        this.working = WorkingThread.workingThread(this.timeModule, null);

        customiseComponents();
        createLayout();

        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                MainWindow.this.starting.cancel(true);
                MainWindow.this.working.interrupt();
                MainWindow.this.timeModule.setEnabled(true);
                MainWindow.this.startButton.setEnabled(true);
                MainWindow.this.statusLabel.setStatus(Status.INACTIVE);
            }

        });
        setSize(320, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void customiseComponents() {
        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.timeModule.setEnabled(false);
                MainWindow.this.startButton.setEnabled(false);
                MainWindow.this.starting = new StartingWorker(MainWindow.this.statusLabel);
                MainWindow.this.starting.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ((evt.getNewValue() == SwingWorker.StateValue.DONE) && (!MainWindow.this.starting.isCancelled())) {
                            MainWindow.this.working = WorkingThread.workingThread(MainWindow.this.timeModule, MainWindow.this.actionsManager.getActions());
                            MainWindow.this.working.addThreadListener(new ThreadListener() {
                                public void finished() {
                                    MainWindow.this.timeModule.setEnabled(true);
                                    MainWindow.this.startButton.setEnabled(true);
                                    MainWindow.this.statusLabel.setStatus(Status.INACTIVE);
                                }
                            });
                            MainWindow.this.working.start();
                        }
                    }
                });
                MainWindow.this.starting.execute();
            }
        });
        this.stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainWindow.this.starting.cancel(true);
                MainWindow.this.working.interrupt();
                MainWindow.this.timeModule.setEnabled(true);
                MainWindow.this.startButton.setEnabled(true);
                MainWindow.this.statusLabel.setStatus(Status.INACTIVE);
            }
        });
    }

    private void createLayout() {
        JPanel contentPanel = new JPanel(new BorderLayout());

        contentPanel.add(westPanel(), BorderLayout.WEST);
        contentPanel.add(this.actionsManager, BorderLayout.CENTER);

        setContentPane(contentPanel);
    }

    private JPanel westPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(statusTimeAndButtonsPanel(), BorderLayout.NORTH);
        panel.add(this.actionsManager.getControlPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel statusTimeAndButtonsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = -1;
        c.fill = 2;
        c.anchor = 19;
        panel.add(statusPanel(), c);
        panel.add(timePanel(), c);
        c.weighty = 1.0D;
        panel.add(buttonsPanel(), c);
        return panel;
    }

    private JPanel timePanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(4, 1));
        panel.add(labels, BorderLayout.WEST);
        labels.add(new JLabel("Hours:"));
        labels.add(new JLabel("Minutes:"));
        labels.add(new JLabel("Seconds:"));
        labels.add(new JLabel("Milliseconds:"));

        JPanel fields = new JPanel(new GridLayout(4, 1));
        panel.add(fields, BorderLayout.CENTER);
        fields.add(this.timeModule.getHoursField());
        fields.add(this.timeModule.getMinutesField());
        fields.add(this.timeModule.getSecondsField());
        fields.add(this.timeModule.getMillisecondsField());

        return panel;
    }

    private JPanel statusPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel("Status:"));
        panel.add(this.statusLabel);
        return panel;
    }

    private JPanel buttonsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(this.startButton);
        panel.add(this.stopButton);
        return panel;
    }

}
