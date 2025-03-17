package voicerecogpackage;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MathCommand extends Command {
    private int firstNumber;
    private int secondNumber;
    private String operation;
    private String operationSymbol; //used for the gui output
    private int currStep;
    private int mathResult;
    private JLabel mathCalculationLabel;

    public MathCommand(String dicPath, String lmPath) {
        super(dicPath, lmPath);
        operation = "";
        operationSymbol = "";
        currStep = 1;
    }

    @Override
    public boolean execute() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("Math command execute");
        showTalkMessage("Say First Number");

        while ((result = getRecognizer().getResult()) != null) {
            inputCommand = result.getHypothesis();
            System.out.println("Input MathCommand: " + inputCommand);

            if (inputCommand.toLowerCase().equals("exit")) {
                System.out.println("Exit command");
                return true;
            }

            if ((!inputCommand.equals(" ") || !inputCommand.equals("")) && currStep==1) {
                //We're going to first listen for the first number (when currStep == 1)
                firstNumber = getFullNumFromStr(inputCommand);
                System.out.println("First Number: " + firstNumber);
                currStep++;
                removeTalkMessage();
                showTalkMessage("Say Math Operation");
            }
            else if ((!inputCommand.equals(" ") || !inputCommand.equals("")) && currStep==2) {
                //We're then going to listen for the type of the operation
                operation = inputCommand.toLowerCase();
                System.out.println("Operation: " + operation);
                currStep++;
                doCalculation(firstNumber,secondNumber,operation); //this is to get operation symbol
                removeTalkMessage();
                showTalkMessage("Say Second Number");
            }
            else if ((!inputCommand.equals(" ") || !inputCommand.equals("")) && currStep==3) {
                //Now efore doing the operation, we're going to listen to the second number
                secondNumber = getFullNumFromStr(inputCommand);
                System.out.println("Second Number: " + secondNumber);
                currStep++;
                removeTalkMessage();
            }
            if (currStep==4) {
                //Finally do the calculation
                mathResult = doCalculation(firstNumber, secondNumber, operation);
                System.out.println("Result: " + mathResult);

                //Making the output show up
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                MathCommandOutput mathCommandOutput = new MathCommandOutput(frame,panel);
                mathCommandOutput.outputResult();
//                return true;
            }
        }
        return false;
    }

    //Function to do math calculations
    private int doCalculation(int firstNumber, int secondNumber, String operation) {
        int result = 0;
        if (operation.equals("plus")) {
            result = firstNumber + secondNumber;
            operationSymbol = "+";
        }
        else if (operation.equals("minus") || operation.equals("subtracted by")) {
            result = firstNumber - secondNumber;
            operationSymbol = "-";
        }
        else if (operation.equals("times")) {
            result = firstNumber * secondNumber;
            operationSymbol = "*";
        }
        else if (operation.equals("divided by") || operation.equals("over")) {
            result = firstNumber / secondNumber;
            operationSymbol = "/";
        }
        else if (operation.equals("power of") || operation.equals("power")) {
            for (int a = 0; a < secondNumber; a++) {
                result *= firstNumber;
            }
        }
        return result;
    }

    //Getting the number from the str (inputCommand)
    private int getFullNumFromStr(String str) {
        String fullNumStr = "";
        String charNumStr = "";
        for (int a = 0; a < str.length() + 1; a++) {
            //Add the current char letter of the current number
            if (a < str.length() && str.charAt(a) != ' ') {
                charNumStr += str.charAt(a);
//                System.out.println(charNumStr);
            }
            else {
                fullNumStr += turnLettersToNum(charNumStr);
                charNumStr = "";
                System.out.println("Curr fullNumStr: " + fullNumStr);
            }
        }
        return Integer.parseInt(fullNumStr);
    }

    //Turns the letters of one 1-10 number to actual numbers in an str
    private String turnLettersToNum(String oneNumStr) {
        String numStr = "";
        if (oneNumStr.toLowerCase().equals("one")) {
            numStr += "1";
        }
        else if (oneNumStr.toLowerCase().equals("two")) {
            numStr += "2";
        }
        else if (oneNumStr.toLowerCase().equals("three")) {
            numStr += "3";
        }
        else if (oneNumStr.toLowerCase().equals("four")) {
            numStr += "4";
        }
        else if (oneNumStr.toLowerCase().equals("five")) {
            numStr += "5";
        }
        else if (oneNumStr.toLowerCase().equals("six")) {
            numStr += "6";
        }
        else if (oneNumStr.toLowerCase().equals("seven")) {
            numStr += "7";
        }
        else if (oneNumStr.toLowerCase().equals("eight")) {
            numStr += "8";
        }
        else if (oneNumStr.toLowerCase().equals("nine")) {
            numStr += "9";
        }
        else if (oneNumStr.toLowerCase().equals("ten")) {
            numStr += "10";
        }
        else if (oneNumStr.toLowerCase().equals("zero")) {
            numStr += "0";
        }
        return numStr;
    }

    //Overrides the show message from command to show a new label containing calculation
    @Override
    public void showTalkMessage(String message) {
        super.showTalkMessage(message);

        getMessageFrame().setVisible(false); //preparing our frame for changes

        //Initializing our new label
        if (currStep==1) {
            mathCalculationLabel = new JLabel(firstNumber + "");
        }
        else if (currStep==2) {
            getMessagePanel().remove(mathCalculationLabel);
            mathCalculationLabel.setText(firstNumber + " " + operationSymbol);
        }
        else if (currStep==3) {
            mathCalculationLabel = new JLabel(firstNumber + " " + operationSymbol);
        }

        mathCalculationLabel.setPreferredSize(new Dimension(150,50));
        getMessagePanel().add(mathCalculationLabel);

        getMessageFrame().setVisible(true);
    }

    //Overrides the remove message from command to also reset our new mathCalculationLabel
    @Override
    public void removeTalkMessage() {
        super.removeTalkMessage();
        getMessagePanel().remove(mathCalculationLabel);
    }

    //Outputs the result in a GUI
    private class MathCommandOutput {
        private JFrame frame;
        private JPanel panel;

        private MathCommandOutput(JFrame frame, JPanel panel) {
            this.frame = frame;
            this.panel = panel;
        }

        private void outputResult() {
            //Initializing frame stuff
            frame.setSize(300,200);
            frame.setTitle("Math Output");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);

            //Initializing panel stuff
            frame.add(panel);

            //Initializing label with result
            JLabel mathResultText = new JLabel(firstNumber + " " + operationSymbol + " " + secondNumber + " = " + mathResult);
            mathResultText.setFont(new Font("SignPainter",Font.BOLD,35));
            panel.add(mathResultText);

            //making sure frame is visible
            frame.setVisible(true);
        }
    }
}
