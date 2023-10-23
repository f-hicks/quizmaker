package com.fhicks.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.InterruptedException;

public class quizUi {
    static private JPanel panel;
    static private JFrame frame;
    static private JButton buttonA;
    static private JButton buttonB;
    static private JButton buttonC;
    static private JButton buttonD;
    static private JLabel question;
    static private int questionnum;
    public int score;
    public Boolean finished = false;

    class answerbutton extends JButton {

        public answerbutton(String text, Color color){
            super(text);
            // Set button borders with different colors
            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 4),
                BorderFactory.createLineBorder(color, 4)
            ));
            // Set button foreground colors
            this.setForeground(Color.WHITE);
            // Set button background colors
            this.setFont(new Font("ARIAL", 10, 80));
            this.setBackground(Color.decode("#1f1f1f"));
            this.setFocusable(false);
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

    public void updateButtonFontSize(JButton[] buttons, double width) {
        width = width / 2;
        int maxWidth = 0;
        int currentFontSize = buttons[0].getFont().getSize();
        String longestString = "";
        for (JButton button : buttons) {
            Font font = button.getFont();
            FontMetrics fontMetrics = button.getFontMetrics(font);
            int stringWidth = fontMetrics.stringWidth(button.getText());
            if (stringWidth > maxWidth) {
                maxWidth = stringWidth;
                longestString = button.getText();
            }
        }
        System.out.println(longestString);
        
        double widthRatio = (((double) width*0.9)/ ((double) maxWidth));
        int newFontSize = (int) ((currentFontSize * widthRatio));
        Font scaledFont = buttons[0].getFont().deriveFont((float) newFontSize);
        for (JButton button : buttons) {
            button.setFont(scaledFont);
        }
        System.out.println(widthRatio);
        System.out.println(newFontSize);
    }

    public quizUi(final String[][] questions){
        questionnum = 0;
        panel = new JPanel(new GridLayout(2, 2));
        buttonA = new answerbutton(questions[questionnum][1], Color.RED);
        buttonB = new answerbutton(questions[questionnum][2], Color.GREEN);
        buttonC = new answerbutton(questions[questionnum][3], Color.BLUE);
        buttonD = new answerbutton(questions[questionnum][4], Color.YELLOW);


        //set action listeners by iterating through the buttons and assigning them to the correct answer
        JButton[] buttons = {buttonA, buttonB, buttonC, buttonD};
        updateButtonFontSize(buttons, Toolkit.getDefaultToolkit().getScreenSize().getWidth());

        String[] chars = {"A", "B", "C", "D"};

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            String qchar = chars[i];

            button.addActionListener(new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if ((qchar.equalsIgnoreCase(questions[questionnum][5]) == true) ||
                        (qchar.equalsIgnoreCase(questions[questionnum][6])))
                        {button.setBackground(Color.GREEN);} 
                    } catch (ArrayIndexOutOfBoundsException e_) { 
                        button.setBackground(Color.RED);
                    }
                    new Thread(() -> {
                        checkAnswer(buttonA, question, qchar, questions);
                    }).start();

                }
            });
            panel.add(button);
        }


        UIManager.put("Button.select", Color.decode("#1f1f1f"));
        
        frame = new JFrame();
        
        frame.setUndecorated(true);
        frame.setResizable(false);
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
        frame.pack();

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

        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
        //.setFullScreenWindow(frame);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
        
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

    private void checkAnswer(JButton buttonPressedObj, JLabel label, String buttonPressed, String[][] questions) {
//        System.out.println(buttonPressed);
//        System.out.println(questions[questionnum][5]);
        if (buttonPressed.equalsIgnoreCase(questions[questionnum][5]) == true){
            System.out.println("Correct");
            ++score;

        } else 
        try {
            if (questions[questionnum][6] != null || (buttonPressed.equalsIgnoreCase(questions[questionnum][6]))){
                System.out.println("Correct");
                ++score;
            } 
        } catch (ArrayIndexOutOfBoundsException e_) { 
            System.out.println("Incorrect");    
        }
        //buttonA.setEnabled(false);
        //buttonB.setEnabled(false);
        //buttonC.setEnabled(false);
        //buttonD.setEnabled(false);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        buttonPressedObj.setBackground(Color.decode("#1f1f1f"));
        ++questionnum;

        if (questionnum == questions.length){
            System.out.println("Score: " + score);
            panel.setVisible(false);
            question.setVisible(false);
            frame.dispose();
            finished = true;
            return;
        }
        JButton[] buttons = {buttonA, buttonB, buttonC, buttonD};
        int count = 1;

        for (JButton button : buttons) {
            button.setBackground(Color.decode("#1f1f1f"));
            button.setText(questions[questionnum][count]);
            button.setForeground(Color.WHITE);
            button.setEnabled(true);
            ++count;
        } 
        updateButtonFontSize(buttons, 800);
        question.setText(questions[questionnum][0]);
        updateTitleFontSize(question, Toolkit.getDefaultToolkit().getScreenSize().width);


    }
}
