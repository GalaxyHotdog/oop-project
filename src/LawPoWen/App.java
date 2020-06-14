package LawPoWen;

import ChenYu.Derivate;
import TanHengJie.*;
import XieMingXuan.BaseCalc.BaseConversion;
import XieMingXuan.IntegralPackage.DefiniteIntegral;
import XieMingXuan.IntegralPackage.IndefiniteIntegral;
import XieMingXuan.Statistics.BivariateStatistics;
import XieMingXuan.Statistics.LinearRegression;
import XieMingXuan.Statistics.QuadraticFitting;
import XieMingXuan.Statistics.UnivariateStatistics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import static java.lang.String.format;

public class App  extends JFrame{
    String sum="";                           //存放text的内容
    String calcprocess="";                  //过程
    String tmp = "";
    String[] stacking = new String[10];
    int stackPointer = 0;
    int mode = 0;

    private JPanel panelMain;
    private JButton num0;
    private JButton buttonSpace;
    private JButton num1;
    private JButton num2;
    private JButton num3;
    private JButton num4;
    private JButton num5;
    private JButton num6;
    private JButton buttonEquals;
    private JButton buttonAdd;
    private JButton buttonMinus;
    private JButton num7;
    private JButton num8;
    private JButton num9;
    private JButton buttonMultiply;
    private JButton AllClear;
    private JButton Delete;
    private JButton buttonMod;
    private JButton buttonDivide;
    private JButton Dot;
    private JButton Buttonπ;
    private JButton Buttone;
    private JButton rightBracket;
    private JButton leftBracket;
    private JComboBox modeCal;
    private JTextArea result;
    private JTextField process;
    private JButton sinButton;
    private JButton cosButton;
    private JButton tanButton;
    private JButton buttonPower;
    private JButton logButton;
    private JButton ButtonFactorial;
    private JButton xButton;
    private JButton lnButton;
    private JButton enterButton;
    private JTextArea Remind;

    public App() {
        super("Calculator");//设定标题
        clearStacking();
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == num0) {
                    calcprocess += "0";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num1) {
                    calcprocess += "1";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num2) {
                    calcprocess += "2";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num3) {
                    calcprocess += "3";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num4) {
                    calcprocess += "4";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num5) {
                    calcprocess += "5";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num6) {
                    calcprocess += "6";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num7) {
                    calcprocess += "7";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num8) {
                    calcprocess += "8";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == num9) {
                    calcprocess += "9";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == Dot) {
                    calcprocess += ".";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonSpace){
                    if(mode == 7)
                        calcprocess += ",";
                    else
                        calcprocess += " ";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonAdd) {
                    calcprocess += "+";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonMinus) {
                    calcprocess += "-";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonMultiply) {
                    calcprocess += "×";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonDivide) {
                    calcprocess += "÷";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonMod) {
                    calcprocess += "mod";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == leftBracket) {
                    calcprocess += "(";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == rightBracket) {
                    calcprocess += ")";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == Delete) {
                    if(!calcprocess.equals(""))
                        calcprocess = calcprocess.substring(0, calcprocess.length() - 1);
                    process.setText(calcprocess);
                }
                else if(e.getSource() == AllClear) {
                    calcprocess = "";
                    tmp = "";
                    process.setText(calcprocess);
                    Remind.setText(tmp);
                    result.setText("0");
                    stackPointer = 0;
                    clearStacking();
                }
                else if(e.getSource() == Buttone) {
                    calcprocess += "e";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == Buttonπ) {
                    calcprocess += "π";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == logButton) {
                    calcprocess += "log";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == ButtonFactorial) {
                    calcprocess += "!";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == sinButton) {
                    calcprocess += "sin";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == cosButton) {
                    calcprocess += "cos";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == tanButton) {
                    calcprocess += "tan";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == buttonPower) {
                    if(mode == 7)
                        calcprocess += "Math.pow";
                    else
                        calcprocess += "^";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == xButton) {
                    calcprocess += "x";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == lnButton) {
                    calcprocess += "ln";
                    process.setText(calcprocess);
                }
                else if(e.getSource() == enterButton) {

                    stacking[stackPointer] += calcprocess;
                    System.out.println(calcprocess + stacking[stackPointer]);
                    stackPointer++;
                    tmp += (calcprocess + "\n");
                    calcprocess = "";
                    process.setText(calcprocess);
                    Remind.setText(tmp);
                }
                else if(e.getSource() == buttonEquals) {
                    switch (mode) {
                        case 0://常规(弧度)//done
                        {
                            sum = format("%f", new Elementary().calculate(calcprocess, true));
                            result.setText(sum);
                            sum = "0";
                            break;
                        }
                        case 1://矩阵四则运算//done
                        {
                            MatrixCalculator cal = new MatrixCalculator();
                            System.out.println(stacking[0] + "stacking");
                            if (stacking[0].equals("+") && setMatrix(cal)) {
                                result.setText(cal.Mat_add(cal.getA(), cal.getB()));
                            } else if (stacking[0].equals("-")  && setMatrix(cal)) {
                                result.setText(cal.Mat_minus(cal.getA(), cal.getB()));
                            } else if (stacking[0].equals("×")) {
                                if (!stacking[3].contains(" ") && cal.SetValueA(stacking[2]).equals("good")) {
                                    cal.MatrixA(stacking[1]);
                                    result.setText(cal.MatMulConst(Double.parseDouble(stacking[3])));
                                } else {
                                    if(setMatrix(cal))
                                        result.setText(cal.Mat_mul(cal.getA(), cal.getB()));
                                    else
                                        result.setText("Invalid Input.");
                                }
                            } else if (stacking[0].equals("÷") && cal.SetValueA(stacking[2]).equals("good")) {
                                cal.MatrixA(stacking[1]);
                                result.setText(cal.MatDivConst(Double.parseDouble(stacking[3])));
                            } else if (stacking[0].equals("-1") && cal.SetValueA(stacking[2]).equals("good")) {
                                cal.MatrixA(stacking[1]);
                                result.setText(cal.Mat_tranverse());
                            } else {
                                result.setText("Error");
                            }
                            sum = "0";
                            break;
                        }
                        case 2://det//done
                        {
                            MatrixCalculator cal = new MatrixCalculator();
                            cal.MatrixA(stacking[0]);
                            if (cal.SetValueA(stacking[1]).equals("Error")) {
                                result.setText("Invalid Input!");
                            } else
                                result.setText(DoubleToString(cal.Mat_det(cal.getA())));
                            break;
                        }
                        case 3://逆矩阵 //done
                        {
                            MatrixCalculator cal = new MatrixCalculator();
                            cal.MatrixA(stacking[0]);
                            if (cal.SetValueA(stacking[1]).equals("Error")) {
                                result.setText("Invalid Input!");
                            } else
                                result.setText(cal.PrintMat(cal.invert(cal.getA())));
                            break;
                        }
                        case 4://多元一次方程 //done
                        {
                            String element = "";
                            for(int i = 1; stacking[i] != ""; i++) {
                                element += (stacking[i] + " ");
                            }
                            sum = Equation.Linear(stacking[0],element.split(" "));
                            result.setText(sum);
                            break;
                        }
                        case 5://一元二次方程 //done
                        {
                            sum = Equation.Quadratic(stacking[0],stacking[1],stacking[2]);
                            result.setText(sum);
                            break;
                        }
                        case 6://一元三次方程 //done
                        {
                            sum = Equation.cubic(stacking[0],stacking[1],stacking[2],stacking[3]);
                            result.setText(sum);
                            break;
                        }
                        case 7://定积分 //done
                        {
                            try {
                                sum = DefiniteIntegral.run(stacking[0],stacking[1],stacking[2]);
                            } catch (Exception ex) {
                                sum = "Error";
                            }finally {
                                result.setText(sum);
                            }
                            break;
                        }
                        case 8://不定积分 //done
                        {
                            sum = IndefiniteIntegral.run(Integer.parseInt(calcprocess));
                            result.setText(sum);
                            break;
                        }
                        case 9://单变量统计
                        {
                            //UnivariateStatistics s = new UnivariateStatistics();
                            try {
                                sum = UnivariateStatistics.rundata(calcprocess);
                            } catch (Exception ex) {
                                sum = "Error";
                            }
                            result.setText(sum);
                            break;
                        }
                        case 10://线性回归统计
                        {
                            sum = new LinearRegression().rundata(stacking[1],Integer.parseInt(stacking[0]));
                            result.setText(sum);
                            break;
                        }
                        case 11://二次回归统计
                        {
                            sum = new QuadraticFitting().rundata(stacking[1],Integer.parseInt(stacking[0]));
                            result.setText(sum);
                            break;
                        }
                        case 12://基数计算 //done
                        {
                            BaseConversion base = new BaseConversion();
                            System.out.println(base.run(calcprocess));
                            result.setText(base.run(calcprocess));
                            break;
                        }
                        case 13://导数 //done
                        {
                            String d = calcprocess.split(" ")[2]; //toDerivate
                            String x = calcprocess.split(" ")[1]; //value of x
                            String n = calcprocess.split(" ")[0]; //value if n
                            tmp = "Value of n : " + n + "\nValue of x : " + x + "\nToDerivate : " + d;
                            Remind.setText(tmp);
                            try {
                                sum = Derivate.run(d, Double.parseDouble(x), Integer.parseInt(n));
                            } catch (Exception ex) {
                                sum = "Error";
                            } finally {
                                result.setText(sum);
                            }
                            break;
                        }
                        case 14://常规(角度)//done
                        {
                            sum = format("%f", new Elementary().calculate(calcprocess, false));
                            result.setText(sum);
                            sum = "0";
                            break;
                        }
                    }
                    calcprocess = "";
                    tmp = "";
                }
            }
        };
        num0.addActionListener(listener);
        num1.addActionListener(listener);
        num3.addActionListener(listener);
        num2.addActionListener(listener);
        num4.addActionListener(listener);
        num6.addActionListener(listener);
        num5.addActionListener(listener);
        num7.addActionListener(listener);
        num8.addActionListener(listener);
        num9.addActionListener(listener);
        buttonAdd.addActionListener(listener);
        buttonDivide.addActionListener(listener);
        buttonEquals.addActionListener(listener);
        buttonMinus.addActionListener(listener);
        buttonMod.addActionListener(listener);
        buttonMultiply.addActionListener(listener);
        buttonSpace.addActionListener(listener);
        buttonPower.addActionListener(listener);
        cosButton.addActionListener(listener);
        sinButton.addActionListener(listener);
        tanButton.addActionListener(listener);
        rightBracket.addActionListener(listener);
        leftBracket.addActionListener(listener);
        Buttone.addActionListener(listener);
        ButtonFactorial.addActionListener(listener);
        Buttonπ.addActionListener(listener);
        Dot.addActionListener(listener);
        AllClear.addActionListener(listener);
        Delete.addActionListener(listener);
        logButton.addActionListener(listener);
        lnButton.addActionListener(listener);
        xButton.addActionListener(listener);
        enterButton.addActionListener(listener);
        modeCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(modeCal.getSelectedItem().equals("常规(弧度)")){
                    mode = 0;
                    calcprocess = "";
                }
                else if(modeCal.getSelectedItem().equals("矩阵四则运算")){
                    mode = 1;
                    calcprocess = "";
                }
                else if(modeCal.getSelectedItem().equals("det")){
                    mode = 2;
                    calcprocess = "";
                }
                else if(modeCal.getSelectedItem().equals("逆矩阵")){
                    mode = 3;
                    calcprocess = "";
                }
                else if(modeCal.getSelectedItem().equals("多元一次方程")){
                    mode = 4;
                    calcprocess = "";
                    tmp = "Way to input : n\n" +
                            "a11 a12 a13 a14 a15 a16 ... a1n\n" +
                            "a21 a22 ...\n" +
                            "...\n" +
                            "an1 an2 ...\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("一元二次方程")){
                    mode = 5;
                    calcprocess = "";
                    tmp = "Way to input:\na\nb\nc\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("一元三次方程")){
                    mode = 6;
                    calcprocess = "";
                    tmp = "Way to input:\na\nb\nc\nd\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("定积分")){
                    mode = 7;
                    calcprocess = "";
                    tmp = "Way to input : \nexpression \nupperLimit \nlowerLimit \n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("不定积分")){
                    mode = 8;
                    calcprocess = "";
                    tmp = "option==1 表示查询幂函数\n" +
                            "option==2 表示查询指数函数\n" +
                            " option==3 表示查询对数函数\n" +
                            "option==4 表示查询三角函数\n" +
                            "option==5 表示查询反三角函数\n" +
                            "option==6 表示查询常数函数\n" + "Way to input : option\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("单变量统计")){
                    mode = 9;
                    calcprocess = "";
                    tmp = "Way to input:a b c d ...\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("线性回归统计")){
                    mode = 10;
                    calcprocess = "";
                    tmp = "选择：option==1 表示计算变量\n" + "option==2 表示回归计算\n输入数据：\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("二次回归统计")){
                    mode = 11;
                    calcprocess = "";
                    tmp = "选择：option==1 表示进行变量计算\n" + "option==2 表示进行二次拟合\n输入数据：\n";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("基数计算")){
                    mode = 12;
                    calcprocess = "";
                    tmp = "Way to input : number oldBase newBase";
                    Remind.setText(tmp);
                }
                else if(modeCal.getSelectedItem().equals("导数")){
                    mode = 13;
                    calcprocess = "";
                }
                else if(modeCal.getSelectedItem().equals("常规(角度)")){
                    mode = 14;
                    calcprocess = "";
                }
            }
        });
    }

    public boolean setMatrix(MatrixCalculator cal){
        cal.MatrixA(stacking[1]);
        if(cal.SetValueA(stacking[2]).equals("Error"))
            return false;
        cal.MatrixB(stacking[3]);
        if(cal.SetValueB(stacking[4]).equals("Error"))
            return false;
        return true;
    }

    public void clearStacking(){
        for(int i = 0; i < stacking.length; i++) {
            stacking[i] = "";
        }
    }

    public static String DoubleToString(double dou) {
        Double dou_obj = new Double(dou);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        String dou_str = nf.format(dou_obj);
        return dou_str;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设定关闭窗体时退出程序
        frame.pack();
        frame.setVisible(true);
    }
}
