/*     */ package uk.co.jpawlak.clicker.actions.gui;
/*     */ 
/*     */

import uk.co.jpawlak.clicker.actions.Action;
import uk.co.jpawlak.clicker.actions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
/*     */ public class ActionsManager
/*     */   extends JPanel
/*     */ {
/*     */   private final Frame parentComponent;
/*     */   private final Actions actions;
/*     */   private final JList<Action> list;
/*     */   private final JButton sleepButton;
/*     */   private final JButton mouseButtonPressButton;
/*     */   private final JButton mouseButtonReleaseButton;
/*     */   private final JButton deleteButton;
/*     */   private final JButton moveUpButton;
/*     */   private final JButton moveDownButton;
/*     */   private final JButton copyButton;
/*     */   
/*     */   public ActionsManager(Frame parentComponent)
/*     */   {
/*  36 */     super(new GridLayout(1, 1));
/*  37 */     this.parentComponent = parentComponent;
/*  38 */     this.actions = new Actions();
/*  39 */     this.actions.add(MouseButtonPressAction.press(true, false, false));
/*  40 */     this.actions.add(MouseButtonReleaseAction.release(true, false, false));
/*  41 */     this.list = new JList(this.actions.asArray());
/*  42 */     this.sleepButton = new JButton("Sleep");
/*  43 */     this.mouseButtonPressButton = new JButton("Press mouse button");
/*  44 */     this.mouseButtonReleaseButton = new JButton("Release mouse button");
/*  45 */     this.deleteButton = new JButton("Delete");
/*  46 */     this.moveUpButton = new JButton("Move up");
/*  47 */     this.moveDownButton = new JButton("Move down");
/*  48 */     this.copyButton = new JButton("Copy");
/*  49 */     customiseComponents();
/*  50 */     add(new JScrollPane(this.list));
/*     */   }
/*     */   
/*     */   public JPanel getControlPanel() {
/*  54 */     JPanel panel = new JPanel(new GridLayout(0, 1));
/*     */     
/*  56 */     JLabel addLabel = new JLabel("Add:");
/*  57 */     addLabel.setHorizontalAlignment(0);
/*  58 */     panel.add(addLabel);
/*  59 */     panel.add(this.sleepButton);
/*  60 */     panel.add(this.mouseButtonPressButton);
/*  61 */     panel.add(this.mouseButtonReleaseButton);
/*     */     
/*  63 */     JLabel modifyLabel = new JLabel("Modify:");
/*  64 */     modifyLabel.setHorizontalAlignment(0);
/*  65 */     panel.add(modifyLabel);
/*  66 */     panel.add(this.deleteButton);
/*  67 */     panel.add(this.moveUpButton);
/*  68 */     panel.add(this.moveDownButton);
/*  69 */     panel.add(this.copyButton);
/*     */     
/*  71 */     return panel;
/*     */   }
/*     */   
/*     */   public Actions getActions() {
/*  75 */     return this.actions;
/*     */   }
/*     */   
/*     */   private void customiseComponents() {
/*  79 */     this.sleepButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  82 */         ActionsManager.this.addAction(SleepAction.showPopup(ActionsManager.this.parentComponent));
/*     */       }
/*  84 */     });
/*  85 */     this.mouseButtonPressButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  88 */         ActionsManager.this.addAction(MouseButtonPressAction.showPopup(ActionsManager.this.parentComponent));
/*     */       }
/*  90 */     });
/*  91 */     this.mouseButtonReleaseButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  94 */         ActionsManager.this.addAction(MouseButtonReleaseAction.showPopup(ActionsManager.this.parentComponent));
/*     */       }
/*  96 */     });
/*  97 */     this.deleteButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 100 */         if (ActionsManager.this.list.getSelectedIndices().length > 0) {
/* 101 */           ActionsManager.this.actions.deleteActions(ActionsManager.this.list.getSelectedIndices());
/* 102 */           ActionsManager.this.refresh();
/*     */         }
/*     */       }
/* 105 */     });
/* 106 */     this.moveUpButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 109 */         if (ActionsManager.this.list.getSelectedIndices().length > 0) {
/* 110 */           int[] newIndices = ActionsManager.this.actions.moveUp(ActionsManager.this.list.getSelectedIndices());
/* 111 */           ActionsManager.this.refresh();
/* 112 */           ActionsManager.this.list.setSelectedIndices(newIndices);
/*     */         }
/*     */       }
/* 115 */     });
/* 116 */     this.moveDownButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 119 */         if (ActionsManager.this.list.getSelectedIndices().length > 0) {
/* 120 */           int[] newIndices = ActionsManager.this.actions.moveDown(ActionsManager.this.list.getSelectedIndices());
/* 121 */           ActionsManager.this.refresh();
/* 122 */           ActionsManager.this.list.setSelectedIndices(newIndices);
/*     */         }
/*     */       }
/* 125 */     });
/* 126 */     this.copyButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 129 */         if (ActionsManager.this.list.getSelectedIndices().length > 0) {
/* 130 */           int[] newIndices = ActionsManager.this.actions.copy(ActionsManager.this.list.getSelectedIndices());
/* 131 */           ActionsManager.this.refresh();
/* 132 */           ActionsManager.this.list.setSelectedIndices(newIndices);
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void addAction(Action action) {
/* 139 */     if (action != null) {
/* 140 */       this.actions.add(action);
/* 141 */       refresh();
/*     */     }
/*     */   }
/*     */   
/*     */   private void refresh() {
/* 146 */     int[] indices = this.list.getSelectedIndices();
/* 147 */     this.list.setListData(this.actions.asArray());
/* 148 */     this.list.setSelectedIndices(indices);
/*     */   }
/*     */ }


/* Location:              C:\Users\Jarcionek\Desktop\Clicker.jar!\clicker\actions\gui\ActionsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */