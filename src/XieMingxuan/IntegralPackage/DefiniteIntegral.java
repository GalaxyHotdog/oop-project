package XieMingXuan.IntegralPackage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-1
 * @描述 定积分类，是所有定积分的计算方法
 */
public class DefiniteIntegral
{
    private final String expression;  //  计算表达式
    private final double upperLimit;  //  积分上限
    private final double lowerLimit;  //  积分下限
    private final ScriptEngineManager manager = new ScriptEngineManager();
    private final ScriptEngine engine = manager.getEngineByName("js");

    /**
     * DefiniteIntegral 构造方法，用于获取积分上限、积分下限、积分表达式
     *
     * @param expression 积分表达式
     * @param upperLimit 积分上限
     * @param lowerLimit 积分下限
     */
    private DefiniteIntegral(String expression, String upperLimit, String lowerLimit) throws Exception
    {
        try
        {
            this.expression = expression;
            Object o1 = engine.eval(upperLimit);
            Object o2 = engine.eval(lowerLimit);
            if (o1 instanceof Integer)
                this.upperLimit = ((Integer) o1).doubleValue();
            else this.upperLimit = (Double) o1;
            if (o2 instanceof Integer)
                this.lowerLimit = ((Integer) o2).doubleValue();
            else this.lowerLimit = (Double) o2;
        }
        catch (Exception e)
        {
            throw new Exception("输入异常！");
        }
    }

    /**
     * calc 方法，用于实现计算表达式的结果
     *
     * @return 计算结果
     */
    private double calc() throws Exception
    {
        final double eps = 1e-7;
        double result;
        if (upperLimit > lowerLimit)
            result = adaptiveSimpson(lowerLimit, upperLimit, eps, simpson(lowerLimit, upperLimit));
        else if (upperLimit == lowerLimit)
            result = 0.0;
        else result = -adaptiveSimpson(upperLimit, lowerLimit, eps, simpson(upperLimit, lowerLimit));
        return result;
    }

    /**
     * adaptiveSimpson 方法，用于对某一段上的函数进行二次函数自适应拟合，求出二次拟合后的积分值
     *
     * @param l   左边界
     * @param r   右边界
     * @param eps 精度
     * @param res 上一次二分法的结果值,第一次使用时应采用 simpson(lowerLimit, upperLimit)
     * @return 最终的积分值
     */
    private double adaptiveSimpson(double l, double r, double eps, double res) throws Exception
    {
        double mid = (l + r) / 2;
        double leftSum = simpson(l, mid);
        double rightSum = simpson(mid, r);
        if (Math.abs(leftSum + rightSum - res) <= eps)
            return leftSum + rightSum;
        else return adaptiveSimpson(l, mid, eps / 2, leftSum) + adaptiveSimpson(mid, r, eps / 2, rightSum);
    }

    /**
     * simpson 方法，用于对某一段上的函数进行二次函数拟合，求出二次拟合后的积分值
     *
     * @param l 左边界
     * @param r 右边界
     * @return 积分值
     * @throws Exception 计算错误抛出异常
     */
    private double simpson(double l, double r) throws Exception
    {
        return (r - l) / 6 * (getFunctionValue(l) + getFunctionValue(r) + 4 * getFunctionValue((l + r) / 2));
    }

    /**
     * getFunctionValue 方法，用于计算一个函数在 x = x0 处的函数值
     *
     * @param x0 对应的自变量取值
     * @return 因变量的值
     * @throws Exception 计算错误则抛出异常
     */
    private double getFunctionValue(double x0) throws Exception
    {
        double res;
        try
        {
            engine.put("x", x0);
            res = (double) engine.eval(expression);
        }
        catch (Exception e)
        {
            throw new Exception("计算错误！");
        }
        return res;
    }

    /**
     * print 函数，用于打印计算结果
     */
    private void print() throws Exception
    {
        DecimalFormat df = new DecimalFormat("0.0000000");
        System.out.println(df.format(calc()));
    }

    /**
     * printHelp 函数，用于打印帮助
     */
    private static void printHelp()
    {
        System.out.println("在进行计算之前，你需要知道：\n" +
                "1.所有的函数表达式都必须输入最完整形式，不可以省略乘号\n" +
                "2.所有的除加、减、乘、除之外的操作，都需要用 JavaScript 中的函数代替。例如 sin(x) 需要用 Math.sin(x) 代替。\n" +
                "3.积分上、下限都可以出现计算表达式，但是要求同 2");
    }

    /**
     * run 静态方法，用于直接执行定积分操作
     *
     * @throws Exception 出现异常进行抛出
     */
    public static void run() throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入任意字母以查看帮助，输入 q 跳过该步骤：");
        String option = scanner.next();
        if (!option.equals("q")) printHelp();
        System.out.println("请输入被积函数：");
        String DefInt = scanner.next();
        System.out.println("请输入积分下限：");
        String lowerLimit = scanner.next();
        System.out.println("请输入积分上限：");
        String upperLimit = scanner.next();
        DefiniteIntegral integral = new DefiniteIntegral(DefInt, upperLimit, lowerLimit);
        System.out.print("积分结果为：");
        integral.print();
    }

    /**
     * 适应图形界面的 run 方法,直接返回计算结果值
     * @param expression 积分表达式
     * @param upperLimit 积分上限
     * @param lowerLimit 积分下限
     * @return 计算结果
     * @throws Exception 计算错误抛出异常
     */
    public static String run(String expression, String upperLimit, String lowerLimit) throws Exception
    {
        //if (expression.contains("^")) return "表达式中含有非法字符 ^ ,请将其改为 Math.pow() 形式重新输入";

        expression = expression.replace("sin", "Math.sin");
        expression = expression.replace("cos", "Math.cos");
        expression = expression.replace("tan", "Math.tan");
        expression = expression.replace("e", "Math.e");
        expression = expression.replace("π", "Math.pi");
        expression = expression.replace("log(", "Math.log(10,");
        expression = expression.replace("ln", "Math.log");
        expression = expression.replace("×", "*");
        expression = expression.replace("÷", "/");

        DefiniteIntegral integral = new DefiniteIntegral(expression, upperLimit, lowerLimit);
        DecimalFormat df = new DecimalFormat("0.0000000");
        return df.format(integral.calc());
    }
}

