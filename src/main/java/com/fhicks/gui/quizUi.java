package com.fhicks.gui;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class quizUi implements ActionListener {
    
    public static void ui(String[][] questions){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#1f1f1f"));
        frame.add(new JLabel(questions[0][0], JLabel.CENTER),
            BorderLayout.NORTH);

        frame.setSize(300, 300);
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        Action escapeAction = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().setFullScreenWindow(null);
          }
        };
    
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(escapeKeyStroke, "ESCAPE");
        frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);

        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
        .setFullScreenWindow(frame);
        //display the quiz
        //take user input
        //check if user input is correct
        //display score
        //ask if user wants to play again
        //if yes, call ui again
        //if no, exit
        System.out.println("ui");
    }
    public static void Main(String[] args) {
        System.out.println("Hello World");
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("actionPerformed");
    }
}
