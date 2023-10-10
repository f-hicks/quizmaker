package com.fhicks.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class quizUi implements ActionListener {
    static private JPanel panel;
    static private JButton buttonA;
    static private JButton buttonB;
    static private JButton buttonC;
    static private JButton buttonD;
    
    public quizUi(String[][] questions){

        panel = new JPanel(new GridLayout(2, 2));
        buttonA = new JButton(questions[0][1]);
        buttonB = new JButton(questions[0][2]);
        buttonC = new JButton(questions[0][3]);
        buttonD = new JButton(questions[0][4]);

        // Set button borders with different colors
        buttonA.setBorder(BorderFactory.createLineBorder(Color.RED));
        buttonB.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        buttonC.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        buttonD.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // Set button foreground colors
        buttonA.setForeground(Color.WHITE);
        buttonB.setForeground(Color.WHITE);
        buttonC.setForeground(Color.WHITE);
        buttonD.setForeground(Color.WHITE);

        // Set button background colors
        buttonA.setBackground(Color.decode("#1f1f1f"));
        buttonB.setBackground(Color.decode("#1f1f1f"));
        buttonC.setBackground(Color.decode("#1f1f1f"));
        buttonD.setBackground(Color.decode("#1f1f1f"));

        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        buttonD.addActionListener(this);


        panel.add(buttonA);
        panel.add(buttonB);
        panel.add(buttonC);
        panel.add(buttonD);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#1f1f1f"));
        JLabel question = new JLabel(questions[0][0], JLabel.CENTER);
        question.setForeground(Color.WHITE);
        question.setFont(new Font("ARIAL", 10, 40));
        frame.add(question, BorderLayout.NORTH);

        frame.add(panel);

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
        if (e.getSource() == buttonA) {
            System.out.println("A");
        } else if (e.getSource() == buttonB) {
            System.out.println("B");
        } else if (e.getSource() == buttonC) {
            System.out.println("C");
        } else if (e.getSource() == buttonD) {
            System.out.println("D");
        }
    }
}
