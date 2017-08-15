/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatBot.bl;

import ChatBot.dal.FileHandler;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Tabish Ali
 */
public class Conversation {
    
    private ArrayList questions;
    private ArrayList answers;
    private final ArrayList tempQues = new ArrayList();
    private final ArrayList tempAns = new ArrayList();
    private final HashMap queAndAns = new HashMap();
    private final FileHandler fh = new FileHandler();
    
    public void readQuesAndAnsFromFile(){
        Object[] obj;
        obj = fh.readQAFile();
        questions = (ArrayList) obj[0];
        answers = (ArrayList) obj[1];
    }
    
    private String getAnswer(String question){
        
        findQuestion(question);
        int size = tempQues.size(), minED = Integer.MAX_VALUE, temp;
        String que = "", ans = "";
        
        for(int i = 0; i < size; ++i){
            temp = getEditDist(tempQues.get(i).toString(), question);
            if(temp < minED){
                minED = temp;
                que = tempQues.get(i).toString();
                ans = tempAns.get(i).toString();
            }
        }
        System.out.println(que);
        return ans;
    }
    
    private void findQuestion(String str){
        tempQues.clear();
        tempAns.clear();
        
        String questionSave;
        String answerSave;
        for(int i=0;i<questions.size();i++){
            questionSave = questions.get(i).toString().toLowerCase();
            answerSave = answers.get(i).toString().toLowerCase();
            if(questionSave.contains(str)){
                tempQues.add(questionSave);
                tempAns.add(answerSave);
            }
        }
    }
    
    private int getEditDist(String str1, String str2){
        
        int row = str1.length() + 1, col = str2.length() + 1;
        int[][] arr = new int[row][col];
        
        for(int i = 0 ; i < col; ++i)
            arr[i][0] = i;
        
        for(int i = 0; i < row; ++i)
            arr[0][i] = i;
        int min;
        for(int i = 1; i < row; ++i){
            for(int j = 1; j < col; ++j){
                min = Math.min(Math.min(arr[i-1][j], arr[i-1][j-1]), arr[i][j-1]);
                if(str1.charAt(i) == str2.charAt(j)){
                    arr[i][j] = min;
                }
                else{
                    arr[i][j] = min + 1;
                }
            }
        }
        return arr[row-1][col-1];
    }
    
    public void startChat(){
        Scanner scan = new Scanner(System.in);
        String userQue;
        userQue = scan.nextLine();
        
        System.out.println(getAnswer(userQue));
        
    }
    
}
