package uk.co.jpawlak.clicker.gui;

import uk.co.jpawlak.clicker.actions.Actions;
import uk.co.jpawlak.clicker.actions.KeyPressAction;
import uk.co.jpawlak.clicker.actions.KeyReleaseAction;
import uk.co.jpawlak.clicker.gui.action.MouseButtonPressActionCreator;
import uk.co.jpawlak.clicker.gui.action.MouseButtonReleaseActionCreator;
import uk.co.jpawlak.clicker.gui.action.SleepActionCreator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Frame;
import java.awt.GridLayout;

public class ActionsManager extends JPanel {

    private final Frame parentComponent;

    private final ActionsJList list;

    private final JButton sleepButton;
    private final JButton mouseButtonPressButton;
    private final JButton mouseButtonReleaseButton;
    private final JButton keyPressButton;
    private final JButton keyReleaseButton;

    private final JButton deleteButton;
    private final JButton moveUpButton;
    private final JButton moveDownButton;
    private final JButton copyButton;

    private final SleepActionCreator sleepActionCreator = new SleepActionCreator();
    private final MouseButtonPressActionCreator mouseButtonPressActionCreator = new MouseButtonPressActionCreator();
    private final MouseButtonReleaseActionCreator mouseButtonReleaseActionCreator = new MouseButtonReleaseActionCreator();

    public ActionsManager(Frame parentComponent, Actions actions) {
        super(new GridLayout(1, 1));
        this.parentComponent = parentComponent;
        this.list = new ActionsJList(actions);

        this.sleepButton = new JButton("Sleep");
        this.mouseButtonPressButton = new JButton("Press mouse button");
        this.mouseButtonReleaseButton = new JButton("Release mouse button");
        this.keyPressButton = new JButton("Press key");
        this.keyReleaseButton = new JButton("Release key");

        this.deleteButton = new JButton("Delete");
        this.moveUpButton = new JButton("Move up");
        this.moveDownButton = new JButton("Move down");
        this.copyButton = new JButton("Copy");

        addActionListenersToButtons();
        add(new JScrollPane(this.list));
    }

    public JPanel getControlPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JLabel addLabel = new JLabel("Add:");
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(addLabel);
        panel.add(sleepButton);
        panel.add(mouseButtonPressButton);
        panel.add(mouseButtonReleaseButton);
        panel.add(keyPressButton);
        panel.add(keyReleaseButton);

        JLabel modifyLabel = new JLabel("Modify:");
        modifyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(modifyLabel);
        panel.add(deleteButton);
        panel.add(moveUpButton);
        panel.add(moveDownButton);
        panel.add(copyButton);

        return panel;
    }

    public Actions getActions() {
        return list.getActions();
    }

    private void addActionListenersToButtons() {
        sleepButton.addActionListener(e -> list.addAction(sleepActionCreator.create(parentComponent)));
        mouseButtonPressButton.addActionListener(e -> list.addAction(mouseButtonPressActionCreator.create(parentComponent)));
        mouseButtonReleaseButton.addActionListener(e -> list.addAction(mouseButtonReleaseActionCreator.create(parentComponent)));
        keyPressButton.addActionListener(e -> list.addAction(KeyPressAction.showPopup(parentComponent)));
        keyReleaseButton.addActionListener(e -> list.addAction(KeyReleaseAction.showPopup(parentComponent)));

        deleteButton.addActionListener(e -> list.deleteActions());
        moveUpButton.addActionListener(e -> list.moveUp());
        moveDownButton.addActionListener(e -> list.moveDown());
        copyButton.addActionListener(e -> list.copy());
    }

}
