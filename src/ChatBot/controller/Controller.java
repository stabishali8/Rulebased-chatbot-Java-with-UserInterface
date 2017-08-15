/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatBot.controller;

import ChatBot.bl.Conversation;

/**
 *
 * @author Tabish Ali
 */
public class Controller {
    
    public void processRequest(Object obj, int action){
        
        Conversation convo = new Conversation();
        
        switch(action){
            case 1:
                convo.readQuesAndAnsFromFile();
                convo.startChat();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}
