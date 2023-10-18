package com.fhicks.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.InterruptedException;

public class quizUi {
    static private JPanel panel;
    static private JButton buttonA;
    static private JButton buttonB;
    static private JButton buttonC;
    static private JButton buttonD;
    static private JLabel question;
    static private int questionnum;

    class answerbutton extends JButton {

        public answerbutton(String text, Color color){
            super(text);
            // Set button borders with different colors
            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createLineBorder(Color.BLACK, 2)
            ));
            // Set button foreground colors
            this.setForeground(Color.WHITE);
            // Set button background colors
            this.setBackground(Color.decode("#1f1f1f"));
        }
    }
    
    public JLabel updateTitleFontSize(JLabel label, int width) {
        Font font = label.getFont();
        FontMetrics fontMetrics = label.getFontMetrics(font);
        int stringWidth = fontMetrics.stringWidth(label.getText());
        System.out.println(stringWidth);
        int componentWidth = width; // Use frame width instead of question width
        double widthRatio = ((double) componentWidth / (double) stringWidth - 0.1);
        int newFontSize = (int) (font.getSize() * widthRatio);
        Font scaledFont = font.deriveFont((float) newFontSize);
        label.setFont(scaledFont);
        return label;
    }

    public quizUi(final String[][] questions){
        questionnum = 0;
        panel = new JPanel(new GridLayout(2, 2));
        buttonA = new answerbutton(questions[questionnum][1], Color.RED);
        buttonB = new answerbutton(questions[questionnum][2], Color.GREEN);
        buttonC = new answerbutton(questions[questionnum][3], Color.BLUE);
        buttonD = new answerbutton(questions[questionnum][4], Color.YELLOW);


        //set action listeners
        buttonA.addActionListener(e -> checkAnswer(buttonA, question, "A", questions));
        buttonB.addActionListener(e -> checkAnswer(buttonB, question, "B", questions));
        buttonC.addActionListener(e -> checkAnswer(buttonC, question, "C", questions));
        buttonD.addActionListener(e -> checkAnswer(buttonD, question, "D", questions));


        panel.add(buttonA);
        panel.add(buttonB);
        panel.add(buttonC);
        panel.add(buttonD);

        UIManager.put("Button.select", Color.decode("#1f1f1f"));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#1f1f1f"));

        question = new JLabel(questions[questionnum][0], JLabel.CENTER);
        question.setFont(new Font("ARIAL", 10, 40));
        question.setForeground(Color.WHITE);
        updateTitleFontSize(question, Toolkit.getDefaultToolkit().getScreenSize().width);

        question.setPreferredSize(new Dimension(frame.getWidth(), question.getPreferredSize().height));

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

    private void checkAnswer(JButton buttonPressedObj, JLabel frame, String buttonPressed, String[][] questions) {

        System.out.println(buttonPressed);
        if (buttonPressed.equalsIgnoreCase(questions[questionnum][5]) == true){
            System.out.println("Correct");
            buttonPressedObj.setBackground(Color.decode("#1f1f1f"));
            buttonPressedObj.setSelected(false);
            buttonPressedObj.setForeground(Color.GREEN);
            buttonPressedObj.setBackground(Color.GREEN);
        } else 
        try {
            if (questions[questionnum][6] != null || (buttonPressed.equalsIgnoreCase(questions[questionnum][6]))){
                System.out.println("Correct");
                buttonPressedObj.setBackground(Color.decode("#1f1f1f"));
                buttonPressedObj.setSelected(false);
                buttonPressedObj.setForeground(Color.GREEN);
                buttonPressedObj.setBackground(Color.GREEN);
            } 
        } catch (ArrayIndexOutOfBoundsException e) { 
            System.out.println("Incorrect");    
            buttonPressedObj.setBackground(Color.decode("#1f1f1f"));
            buttonPressedObj.setForeground(Color.RED);
            buttonPressedObj.setBackground(Color.RED);
        }

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        buttonPressedObj.setBackground(Color.decode("#1f1f1f"));
        ++questionnum;
        buttonA.setText(questions[questionnum][1]);
        buttonB.setText(questions[questionnum][2]);
        buttonC.setText(questions[questionnum][3]);
        buttonD.setText(questions[questionnum][4]);
        question.setText(questions[questionnum][0]);
        updateTitleFontSize(question, Toolkit.getDefaultToolkit().getScreenSize().width);
        buttonA.setForeground(Color.WHITE);
        buttonB.setForeground(Color.WHITE);
        buttonC.setForeground(Color.WHITE);
        buttonD.setForeground(Color.WHITE);
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);


    }
}
