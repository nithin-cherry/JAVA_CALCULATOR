import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;

public class Calculator {
    int boardWidth = 360, boardHeight = 540;
    Color red = new Color(217, 30, 54);
    Color black = new Color(0, 0, 0);
    Color c_black = new Color(28, 28, 28);
    Color c_red = new Color(115, 0, 0);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    String A = "0";
    String B = null;
    String operator = null; 

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttons = new JPanel();

    Calculator() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(c_black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttons.setLayout(new GridLayout(5, 4));
        buttons.setBackground(c_black);
        frame.add(buttons, BorderLayout.CENTER);

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];

            button.setText(buttonValue);
            button.setFocusable(false);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setBorder(new LineBorder(Color.white));

            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(red);
                button.setForeground(Color.white);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(black);
                button.setForeground(Color.white);
            } else {
                button.setBackground(c_black);
                button.setForeground(Color.white);
            }
            buttons.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton source = (JButton) e.getSource();
                    String buttonValue = source.getText();

                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                        if ( buttonValue == "=")
                        {
                            if ( A!= null )
                            {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                double result = 0;
                        
                                if (operator == "+") {
                                    result = numA + numB;
                                } else if (operator == "-") {
                                    result = numA - numB;
                                } else if (operator == "×") {
                                    result = numA * numB;
                                } else if (operator == "÷") {
                                    result = numA / numB;
                                }
                                displayLabel.setText(removeZero(result));
                            }

                        }
                        else if ("-+×÷".contains(buttonValue)) {
                            if (operator == null) 
                            {
                                A = displayLabel.getText();

                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }

                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {
                        if (buttonValue == "AC") {
                            clearAll();
                            displayLabel.setText(A);
                        } else if (buttonValue == "+/-") {
                            Double numDisp = Double.parseDouble(displayLabel.getText());
                            numDisp = numDisp * (-1);
                            displayLabel.setText(removeZero(numDisp));
                        } else if (buttonValue == "%") {
                            Double numDisp = Double.parseDouble(displayLabel.getText());
                            numDisp = numDisp / 100;
                            displayLabel.setText(removeZero((numDisp)));
                        }

                    } else {
                        if (buttonValue == ".") {
                            if (!displayLabel.getText().contains(buttonValue)) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }

                        } else if ("1234567890".contains(buttonValue)) {
                            if (displayLabel.getText() == "0") {
                                displayLabel.setText(buttonValue);
                            } else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }

                        }
                    }
                }

            });

        }

    }

    void clearAll()
    {
        A = "0";
        B = null;
        operator = null;
    }

    String removeZero(double numDisp)
    {
        if ( numDisp % 1 == 0)
            return Integer.toString((int) numDisp);
        
            return Double.toString(numDisp);
    }
}
        
        

