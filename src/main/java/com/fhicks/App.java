package com.fhicks;
import java.util.*;

import com.fhicks.gui.quizUi;
import com.fhicks.gui.quizUi.*;

import java.io.File;
import java.io.FileNotFoundException;

public class App{

    private static Random rand = new Random();
    
    public static int getfilelength(File file){
        int count = 0;

        try {
            // create an object of Scanner
            // associated with the file
            Scanner sc = new Scanner(file);

            // read each line and
            // count number of lines
            while(sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            System.out.println("Total Number of Lines: " + count);

            // close scanner
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
            return 0;
        }
        return count;
    }

    public static String[][] generate_quiz(){
        //get 20 random questions from the csv file and return a list of questions
        //each question is a list of 5 elements: question, answera, answerb, answerc, answerd, correctanswerletter
        File questionsfile = new File("quizquestions.csv");
        int numquestions = getfilelength(questionsfile);
        String[] questionsraw = new String[numquestions];
        String[][] questions = new String[numquestions][5];
        
        try{
            Scanner filereader = new Scanner(questionsfile);
            
            for (int i = 0; i < numquestions; i++){
                questionsraw[i] = filereader.nextLine();
            }
            filereader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e);
        }
        int count = 0;
        while (count < numquestions){
            questions[count] = questionsraw[count].split(",");
            ++count;
        }

        String[][] questionsused = new String[20][5];
        
        count = 0;
        while (count < 20){
            int randomnum = rand.nextInt(numquestions);
            questionsused[count] = questions[randomnum];
            ++count;
            
        }

        return questionsused;
    }

    public static void main(String[] args){
        String[][] questions = generate_quiz();
        new quizUi();
        quizUi.ui(questions);

        
    }
}
