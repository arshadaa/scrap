/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scrap;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Arshad
 */
public class App {

   
   
    
    public static String getData(String c)throws Exception{
        
        StringBuffer sf = new StringBuffer();
    
        sf.append("<html>"+
                "<body style='text-align:center;color:red;'>");
        String url ="https://www.worldometers.info/coronavirus/country/" + c +"/";
        Document doc = Jsoup.connect(url).get();       //Jsoup.connect(url).get(); it returns html
        //System.out.println(doc.title());
       // System.out.println(doc.body().html());
       //#maincounter-wrap
        Elements elements  = doc.select("#maincounter-wrap");
        //System.out.println(elements.html());
        elements.forEach((e) -> {
        
         //   System.out.println(e.html());
          //  System.out.println("");
           // System.out.println("");
           String text = e.select("h1").text();
           String count = e.select(".maincounter-number>span").text();
           // System.out.println(text +" : "+ count);
           sf.append(text).append(" : ").append(count).append("<br>");
        });
        sf.append("</body>" 
                + "</html>");
      return sf.toString();
    }
    
      public static void main(String[] args)throws Exception {
    
     /*     System.out.println("Hello World");
          Scanner s = new Scanner(System.in);
          System.out.println("Enter country: ");
          String co = s.nextLine();
          System.out.println(getData(co)); ;*/
     
     // Creating GUI
          JFrame root = new JFrame("Details of country");
          root.setSize(500,500);
          
          Font f = new Font("Poppins",Font.BOLD, 30);
          
          //textField
          JTextField field = new JTextField();
          
          //Label
          JLabel label = new JLabel();
          field.setFont(f);
          label.setFont(f);
          field.setHorizontalAlignment(SwingConstants.CENTER);       //put the text in the center 
          label.setHorizontalAlignment(SwingConstants.CENTER);
            //button
          
          JButton button = new JButton("Get");
          
          button.addActionListener(event->{
          
              try{
              
              String maindata = field.getText();
              String result = getData(maindata);
              label.setText(result);
              }catch(Exception e ){e.printStackTrace();}
              
          });
           
          
          button.setFont(f);
          
          root.setLayout(new BorderLayout());
          
          root.add(field,BorderLayout.NORTH);
          root.add(label,BorderLayout.CENTER);
          root.add(button,BorderLayout.SOUTH);
          root.setVisible(true);
      }
}
