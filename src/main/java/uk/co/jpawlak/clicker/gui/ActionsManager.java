package uk.co.jpawlak.clicker.gui;

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.KeyPressAction;
import uk.co.jpawlak.clicker.actions.KeyReleaseAction;
import uk.co.jpawlak.clicker.actions.MouseButtonPressAction;
import uk.co.jpawlak.clicker.actions.MouseButtonReleaseAction;
import uk.co.jpawlak.clicker.actions.SleepAction;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsManager extends JPanel {

    private final Frame parentComponent;

    private final Actions actions;
    private final JList<Action> list;

    private final JButton sleepButton;
    private final JButton mouseButtonPressButton;
    private final JButton mouseButtonReleaseButton;
    private final JButton keyPressButton;
    private final JButton keyReleaseButton;

    private final JButton deleteButton;
    private final JButton moveUpButton;
    private final JButton moveDownButton;
    private final JButton copyButton;

    public ActionsManager(Frame parentComponent) {
        super(new GridLayout(1, 1));
        this.parentComponent = parentComponent;
        this.actions = new Actions();
        this.actions.add(MouseButtonPressAction.press(true, false, false));
        this.actions.add(MouseButtonReleaseAction.release(true, false, false));
        this.list = new JList<>(this.actions.asArray());
        this.sleepButton = new JButton("Sleep");
        this.mouseButtonPressButton = new JButton("Press mouse button");
        this.mouseButtonReleaseButton = new JButton("Release mouse button");
        this.keyPressButton = new JButton("Press key");
        this.keyReleaseButton = new JButton("Release key");
        this.deleteButton = new JButton("Delete");
        this.moveUpButton = new JButton("Move up");
        this.moveDownButton = new JButton("Move down");
        this.copyButton = new JButton("Copy");
        customiseComponents();
        add(new JScrollPane(this.list));
    }

    public JPanel getControlPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JLabel addLabel = new JLabel("Add:");
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(addLabel);
        panel.add(this.sleepButton);
        panel.add(this.mouseButtonPressButton);
        panel.add(this.mouseButtonReleaseButton);
        panel.add(this.keyPressButton);
        panel.add(this.keyReleaseButton);

        JLabel modifyLabel = new JLabel("Modify:");
        modifyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(modifyLabel);
        panel.add(this.deleteButton);
        panel.add(this.moveUpButton);
        panel.add(this.moveDownButton);
        panel.add(this.copyButton);

        return panel;
    }

    public Actions getActions() {
        return this.actions;
    }

    private void customiseComponents() {
        this.sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActionsManager.this.addAction(SleepAction.showPopup(ActionsManager.this.parentComponent));
            }
        });
        this.mouseButtonPressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActionsManager.this.addAction(MouseButtonPressAction.showPopup(ActionsManager.this.parentComponent));
            }
        });
        this.mouseButtonReleaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActionsManager.this.addAction(MouseButtonReleaseAction.showPopup(ActionsManager.this.parentComponent));
            }
        });
        this.keyPressButton.addActionListener(e -> ActionsManager.this.addAction(KeyPressAction.showPopup(ActionsManager.this.parentComponent)));
        this.keyReleaseButton.addActionListener(e -> ActionsManager.this.addAction(KeyReleaseAction.showPopup(ActionsManager.this.parentComponent)));
        this.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ActionsManager.this.actions.deleteActions(ActionsManager.this.list.getSelectedIndices());
                ActionsManager.this.refresh();
                ActionsManager.this.list.setSelectedIndices(new int[] {});
            }
        });
        this.moveUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ActionsManager.this.list.getSelectedIndices().length > 0) {
                    int[] newIndices = ActionsManager.this.actions.moveUp(ActionsManager.this.list.getSelectedIndices());
                    ActionsManager.this.refresh();
                    ActionsManager.this.list.setSelectedIndices(newIndices);
                }
            }
        });
        this.moveDownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ActionsManager.this.list.getSelectedIndices().length > 0) {
                    int[] newIndices = ActionsManager.this.actions.moveDown(ActionsManager.this.list.getSelectedIndices());
                    ActionsManager.this.refresh();
                    ActionsManager.this.list.setSelectedIndices(newIndices);
                }
            }
        });
        this.copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ActionsManager.this.list.getSelectedIndices().length > 0) {
                    int[] newIndices = ActionsManager.this.actions.copy(ActionsManager.this.list.getSelectedIndices());
                    ActionsManager.this.refresh();
                    ActionsManager.this.list.setSelectedIndices(newIndices);
                }
            }
        });
    }

    private void addAction(Action action) {
        if (action != null) {
            this.actions.add(action);
            refresh();
        }
    }

    private void refresh() {
        int[] indices = this.list.getSelectedIndices();
        this.list.setListData(this.actions.asArray());
        this.list.setSelectedIndices(indices);
    }

}
