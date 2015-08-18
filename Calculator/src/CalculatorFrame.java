import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CalculatorFrame extends JFrame implements ActionListener {

    JTextField Attr1, Attr2; // two Attr of calculator
    JLabel TypeJLabel, EquJLabel, ResultJLabel;
    JButton Add, Dec, Mul, Div, Equ;
    CalculatorLogic calculator;

    public CalculatorFrame() {
        super("Calculator By TanX");
        calculator = new CalculatorLogic();
        // text field
        Attr1 = new JTextField();
        Attr2 = new JTextField();
        Attr1.setPreferredSize(new Dimension(100, 100));
        Attr2.setPreferredSize(new Dimension(100, 100));
        // text field end

        // JLabel
        TypeJLabel = new JLabel("", JLabel.CENTER);
        EquJLabel = new JLabel("=", JLabel.CENTER);
        ResultJLabel = new JLabel("", JLabel.CENTER);
        TypeJLabel.setPreferredSize(new Dimension(100, 100));
        EquJLabel.setPreferredSize(new Dimension(100, 100));
        ResultJLabel.setPreferredSize(new Dimension(100, 100));
        // JLabel end

        // JButton
        Add = new JButton("+");
        Dec = new JButton("-");
        Mul = new JButton("*");
        Div = new JButton("/");
        Equ = new JButton("OK");
        Add.setPreferredSize(new Dimension(100, 100));
        Dec.setPreferredSize(new Dimension(100, 100));
        Mul.setPreferredSize(new Dimension(100, 100));
        Div.setPreferredSize(new Dimension(100, 100));
        Equ.setPreferredSize(new Dimension(100, 100));

        // add listener
        Add.addActionListener(this);
        Dec.addActionListener(this);
        Mul.addActionListener(this);
        Div.addActionListener(this);
        Equ.addActionListener(this);

        // JButton end

        // add
        add(Attr1);
        add(TypeJLabel);
        add(Attr2);
        add(EquJLabel);
        add(ResultJLabel);
        add(Add);
        add(Dec);
        add(Mul);
        add(Div);
        add(Equ);
        // add end

        setResizable(false);
        setVisible(true);
        setSize(560, 230);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    }

    public static void main(String[] args) {
        new CalculatorFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // use calctype to judge action
        String CalcType = e.getActionCommand();
        // use switch to pass attributes to CalculatorLogic
        switch (CalcType) {
            case "+":
                TypeJLabel.setText("+");
                calculator.setCurrentType("add");
                break;
            case "-":
                TypeJLabel.setText("-");
                calculator.setCurrentType("dec");
                break;
            case "*":
                TypeJLabel.setText("*");
                calculator.setCurrentType("mul");
                break;
            case "/":
                TypeJLabel.setText("/");
                calculator.setCurrentType("div");
                break;
            case "OK":
                // use pattern to judge valid input
                Pattern patternAttr = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
                Matcher matcherAttr1 = patternAttr.matcher(Attr1.getText());
                Matcher matcherAttr2 = patternAttr.matcher(Attr2.getText());
                if ((!matcherAttr1.matches()) || (!matcherAttr2.matches())) {
                    ResultJLabel.setText("Wrong Input!");
                    break;
                }
                // pattern end
                calculator.setAttr1(Double.parseDouble(Attr1.getText()));
                calculator.setAttr2(Double.parseDouble(Attr2.getText()));
                calculator.calculate();
                ResultJLabel.setText(Double.toString(calculator.getResult()));
                break;
            default:
                break;
        }
    }
}
