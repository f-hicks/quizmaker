package com.fhicks.gui;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Scanner;

public class quizEnd {
    static private String filename = "gradeBoundaries.csv";
    static private String[][] gradeBoundaries;
    static private String grade;

    public static String[][] getGradeBoundaries(String filename) {
        File questionsfile = new File(filename);
        String[][] gradeBoundaries = new String[6][2];

        int count = 0;
        try {
            Scanner sc = new Scanner(questionsfile);
            while (sc.hasNextLine()) {
                gradeBoundaries[count] = sc.nextLine().split(",");
                ++count;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return gradeBoundaries;
    }

    public quizEnd(int score, Double percentage) {
        gradeBoundaries = getGradeBoundaries(filename);
        System.out.println(gradeBoundaries);
        System.out.println(percentage);

        for (int i = 0; i < gradeBoundaries.length; i++) {
            System.out.println(gradeBoundaries[i][0] + " " + gradeBoundaries[i][1]);
            if (percentage >= Double.parseDouble(gradeBoundaries[i][1])) {
                grade = gradeBoundaries[i][0];
                System.out.println(gradeBoundaries[i][0]);
                break;
            }
        }
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        Action escapeAction = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().setFullScreenWindow(null);
          }
        };
        frame.pack();
    
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(escapeKeyStroke, "ESCAPE");
        frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
        
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
        .setFullScreenWindow(frame);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#1f1f1f"));

        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();  

        JTextArea resultBox = new JTextArea();
        resultBox.setEditable(false);
        resultBox.setLineWrap(true);
        resultBox.setWrapStyleWord(true);
        int roundedPercentage = (int) Math.round(percentage * 100);
        resultBox.setFont(new Font("Arial", Font.PLAIN, 50)); // Set font size to 16
        resultBox.setBackground(Color.decode("#1f1f1f"));
        resultBox.setForeground(Color.WHITE);
        resultBox.setText("Score: " + score + "\nGrade: " + grade + "\nPercentage: " + roundedPercentage + "%");
        
        resultBox.setAlignmentX(JLabel.CENTER);
        resultBox.setAlignmentY(Component.CENTER_ALIGNMENT);

        frame.setLayout(new BorderLayout());
        frame.add(resultBox, BorderLayout.CENTER);
    }
        

/*        Dimension size = frame.getContentPane().getSize();
        resultBox.setPreferredSize(size); */
}
