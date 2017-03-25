package com.dilli;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dilli on 3/25/2017.
 */
public class CCValidator extends JFrame{
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JLabel validMessageLabel;

    public CCValidator(){
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setSize(new Dimension(380,310));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = isVisaCardNumberValid(ccNumber);
                if(valid){
                    validMessageLabel.setText("Credit card number is valid");
                }else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(CCValidator.this, "Are you sure you want to quit?",
                        "Quit", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
    private static boolean isVisaCardNumberValid(String cc){
        //public static boolean isVisaCardNumberValid(String cc){
            //This creates a character array.
            char[] characters = new char[cc.length()];
            //changes user entry string to character array.
            characters = cc.toCharArray();
            //creates integer array.
            int[] numbers = new int[characters.length];
            //this changes character's value(ASCII) to integer.
            for(int x = 0; x<numbers.length; x++) {
                numbers[x] = characters[x] - '0';
            }
            int sum = 0;
            for (int x = numbers.length; x > 0; x = x - 2) {
                int Number = numbers[x - 1];
                sum = sum + Number;
            }
            for (int y = (numbers.length - 1); y > 0; y = y - 2){
                int ProductNumber = numbers[y - 1] * 2;
                if (ProductNumber > 9) {
                    sum = sum + ProductNumber % 10+ ProductNumber/10;
                } else {
                    sum = sum + ProductNumber;
                }
            }
            if (sum%10==0&&numbers.length==16&&numbers[0]==4){
                return true;
            }else if(numbers[0]!=4){
                System.out.println("The card number doesn't start with 4.");
            }else if(numbers.length<16){
                System.out.println("The card number is too short.");
            }else{
                System.out.println("The card number is too long.");
            }
            return false;
        }
}

