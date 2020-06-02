package IntegralPackage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @创建人 谢铭轩
 * @创建时间 2020-6-1
 * @描述 定积分类，是所有定积分的计算方法
 */
public abstract class DefiniteIntegral
{
    String expression;  //  计算表达式
    double upperLimit;  //  积分上限
    double lowerLimit;  //  积分下限
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");

    /**
     * getPara 方法，用于获取积分上限、积分下限、积分表达式
     *
     * @param expression 积分表达式
     * @param upperLimit 积分上限
     * @param lowerLimit 积分下限
     */
    public void getLimit(String expression, double upperLimit, double lowerLimit)
    {
        this.expression = expression;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
    }

    /**
     * calc 方法，用于实现计算表达式的结果
     *
     * @return 计算结果
     */
    public double calc() throws Exception
    {
        final double eps = 1e-7;
        double result;
        if (upperLimit < lowerLimit)
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
    public double adaptiveSimpson(double l, double r, double eps, double res) throws Exception
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
    public double simpson(double l, double r) throws Exception
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
    public double getFunctionValue(double x0) throws Exception
    {
        double res;
        try
        {
            engine.put("x", x0);
            res = (double) engine.eval(expression);
        }
        catch (Exception e)
        {
            throw new Exception("计算错误");
        }
        return res;
    }

    /**
     * print 函数，用于打印计算结果
     */
    public void print() throws Exception
    {
        System.out.println(calc());
    }
}
