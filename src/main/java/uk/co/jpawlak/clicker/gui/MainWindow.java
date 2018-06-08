/*     */ package uk.co.jpawlak.clicker.gui;
/*     */ 
/*     */

import uk.co.jpawlak.clicker.actions.gui.ActionsManager;
import uk.co.jpawlak.clicker.threads.StartingWorker;
import uk.co.jpawlak.clicker.threads.ThreadListener;
import uk.co.jpawlak.clicker.threads.WorkingThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ 
/*     */ public class MainWindow extends JFrame
/*     */ {
/*     */   private final TimeModule timeModule;
/*     */   private final StatusLabel statusLabel;
/*     */   private final JButton startButton;
/*     */   private final JButton stopButton;
/*     */   private final ActionsManager actionsManager;
/*     */   private StartingWorker starting;
/*     */   private WorkingThread working;
/*     */   
/*     */   public static JFrame mainWindow()
/*     */   {
/*  35 */     return new MainWindow();
/*     */   }
/*     */   
/*     */   private MainWindow() {
/*  39 */     this.timeModule = new TimeModule();
/*  40 */     this.statusLabel = new StatusLabel();
/*  41 */     this.startButton = new JButton("Start");
/*  42 */     this.stopButton = new JButton("Stop");
/*  43 */     this.actionsManager = new ActionsManager(this);
/*     */     
/*  45 */     this.starting = new StartingWorker(null);
/*  46 */     this.working = WorkingThread.workingThread(this.timeModule, null);
/*     */     
/*  48 */     customiseComponents();
/*  49 */     createLayout();
/*     */     
/*  51 */     addWindowListener(new WindowAdapter()
/*     */     {
/*     */       public void windowActivated(WindowEvent e) {
/*  54 */         MainWindow.this.starting.cancel(true);
/*  55 */         MainWindow.this.working.interrupt();
/*  56 */         MainWindow.this.timeModule.setEnabled(true);
/*  57 */         MainWindow.this.startButton.setEnabled(true);
/*  58 */         MainWindow.this.statusLabel.setStatus(Status.INACTIVE);
/*     */       }
/*     */       
/*  61 */     });
/*  62 */     setSize(320, 480);
/*  63 */     setLocationRelativeTo(null);
/*  64 */     setDefaultCloseOperation(3);
/*  65 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void customiseComponents() {
/*  69 */     this.startButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  72 */         MainWindow.this.timeModule.setEnabled(false);
/*  73 */         MainWindow.this.startButton.setEnabled(false);
/*  74 */         MainWindow.this.starting = new StartingWorker(MainWindow.this.statusLabel);
/*  75 */         MainWindow.this.starting.addPropertyChangeListener(new java.beans.PropertyChangeListener()
/*     */         {
/*     */           public void propertyChange(PropertyChangeEvent evt) {
/*  78 */             if ((evt.getNewValue() == SwingWorker.StateValue.DONE) && (!MainWindow.this.starting.isCancelled())) {
/*  79 */               MainWindow.this.working = WorkingThread.workingThread(MainWindow.this.timeModule, MainWindow.this.actionsManager.getActions());
/*  80 */               MainWindow.this.working.addThreadListener(new ThreadListener()
/*     */               {
/*     */                 public void finished() {
/*  83 */                   MainWindow.this.timeModule.setEnabled(true);
/*  84 */                   MainWindow.this.startButton.setEnabled(true);
/*  85 */                   MainWindow.this.statusLabel.setStatus(Status.INACTIVE);
/*     */                 }
/*  87 */               });
/*  88 */               MainWindow.this.working.start();
/*     */             }
/*     */           }
/*  91 */         });
/*  92 */         MainWindow.this.starting.execute();
/*     */       }
/*  94 */     });
/*  95 */     this.stopButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  98 */         MainWindow.this.starting.cancel(true);
/*  99 */         MainWindow.this.working.interrupt();
/* 100 */         MainWindow.this.timeModule.setEnabled(true);
/* 101 */         MainWindow.this.startButton.setEnabled(true);
/* 102 */         MainWindow.this.statusLabel.setStatus(Status.INACTIVE);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void createLayout() {
/* 108 */     JPanel contentPanel = new JPanel(new BorderLayout());
/*     */     
/* 110 */     contentPanel.add(westPanel(), "West");
/* 111 */     contentPanel.add(this.actionsManager, "Center");
/*     */     
/* 113 */     setContentPane(contentPanel);
/*     */   }
/*     */   
/*     */   private JPanel westPanel() {
/* 117 */     JPanel panel = new JPanel(new BorderLayout());
/* 118 */     panel.add(statusTimeAndButtonsPanel(), "North");
/* 119 */     panel.add(this.actionsManager.getControlPanel(), "South");
/* 120 */     return panel;
/*     */   }
/*     */   
/*     */   private JPanel statusTimeAndButtonsPanel() {
/* 124 */     JPanel panel = new JPanel(new GridBagLayout());
/* 125 */     GridBagConstraints c = new GridBagConstraints();
/* 126 */     c.gridx = 0;
/* 127 */     c.gridy = -1;
/* 128 */     c.fill = 2;
/* 129 */     c.anchor = 19;
/* 130 */     panel.add(statusPanel(), c);
/* 131 */     panel.add(timePanel(), c);
/* 132 */     c.weighty = 1.0D;
/* 133 */     panel.add(buttonsPanel(), c);
/* 134 */     return panel;
/*     */   }
/*     */   
/*     */   private JPanel timePanel() {
/* 138 */     JPanel panel = new JPanel(new BorderLayout(5, 5));
/*     */     
/* 140 */     JPanel labels = new JPanel(new GridLayout(4, 1));
/* 141 */     panel.add(labels, "West");
/* 142 */     labels.add(new JLabel("Hours:"));
/* 143 */     labels.add(new JLabel("Minutes:"));
/* 144 */     labels.add(new JLabel("Seconds:"));
/* 145 */     labels.add(new JLabel("Milliseconds:"));
/*     */     
/* 147 */     JPanel fields = new JPanel(new GridLayout(4, 1));
/* 148 */     panel.add(fields, "Center");
/* 149 */     fields.add(this.timeModule.getHoursField());
/* 150 */     fields.add(this.timeModule.getMinutesField());
/* 151 */     fields.add(this.timeModule.getSecondsField());
/* 152 */     fields.add(this.timeModule.getMillisecondsField());
/*     */     
/* 154 */     return panel;
/*     */   }
/*     */   
/*     */   private JPanel statusPanel() {
/* 158 */     JPanel panel = new JPanel(new FlowLayout());
/* 159 */     panel.add(new JLabel("Status:"));
/* 160 */     panel.add(this.statusLabel);
/* 161 */     return panel;
/*     */   }
/*     */   
/*     */   private JPanel buttonsPanel() {
/* 165 */     JPanel panel = new JPanel(new GridLayout(1, 2));
/* 166 */     panel.add(this.startButton);
/* 167 */     panel.add(this.stopButton);
/* 168 */     return panel;
/*     */   }
/*     */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\gui\MainWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */