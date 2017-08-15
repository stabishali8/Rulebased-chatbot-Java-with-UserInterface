/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatBot.dal;

import ChatBot.constant.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tabish Ali
 */
public class FileHandler {
    
    private BufferedReader br;
    private BufferedWriter bw;
    
    public Object[] readQAFile(){
        
        Object[] objArr = new Object[2];
        try {
            br = new BufferedReader(new FileReader(new File(Constants.QUESTIONS_ANSWERS_FILE_NAME)));
            String temp;
            Scanner readQuestion = new Scanner(br);
            String QA;
            String[] QAsplit;
            List question = new ArrayList<String>();
            List answer = new ArrayList<String>();
            while(readQuestion.hasNextLine()){
                   QA = readQuestion.nextLine();
                   QAsplit = QA.split(",");
                   question.add(QAsplit[0]);
                   answer.add(QAsplit[1]);
            }
            objArr[0] = question;
            objArr[1] = answer;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objArr;
    }
    
    public void writeDataToFile(List questions, List answers){
        
        try {
            bw = new BufferedWriter(new FileWriter(new File(Constants.CHAT_BOT_DATA), true));
            int size = questions.size();
            
            for(int i = 0; i < size; ++i){
                bw.write(questions.get(i) + "," + answers.get(i) + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
