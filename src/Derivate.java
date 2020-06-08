import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class Derivate {
    private Token exper=null;
    public static void main(String[] args){
        String arg="xsin(x+e^x*3)*(2+23)";
        try {
            Derivate x=new Derivate(arg);
            System.out.println(x.value(2));
            System.out.println(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Derivate(String arg) throws Exception{
        Vector<Token> s=ToToken(arg);
        new Check(s);
        Stack<Token> stack=new Stack();
        Stack<Integer> stack0=new Stack();
        for (int i = 0; i < s.size(); i++) {
            Token x = s.elementAt(i);
            if (x.mark == 0) {
                stack.push(x);
                stack0.push(i);
            }
            else if (x.mark == 1) {
                Token y = stack.pop();
                Integer m = stack0.pop();
                Token.create(s.subList(m, i + 1),false);
                i = 0;
            }
        }
        Token.create(s.subList(0,s.size()),true);
        exper=s.elementAt(0);

    }
    public double value(double x) throws Exception{
        return (double)Math.round(exper.derivate().value(x)*10000)/10000;
    }
    private static Vector<Token> ToToken(String arg){
        Vector<Token> s=new Vector<>();
        boolean flag=true;
        for (int i = 0; i < arg.length(); i++) {
            Token x = null;
            char c=arg.charAt(i);
            if(c>='0'&&c<='9'){
                int t=i;
                while(t<arg.length()&&arg.charAt(t)>='0'&&arg.charAt(t)<='9')
                    t++;
                x=new Token(arg.substring(i,t),2);

                i=t-1;flag=true;
            }
            else if(c=='x'){
                if(s.size()!=0&&(s.lastElement().mark==2||s.lastElement().mark==3))
                s.add(new Token("*",5));
                x=new Token("x",3);
                flag=true;
            }

            else if(c=='e')
                x=new Token("e",2);
            else if(c>='a'&&c<='z'){
                int t=i;
                while(t<arg.length()&&arg.charAt(t)>='a'&&arg.charAt(t)<='z'&&arg.charAt(t)!='x')
                    t++;
                if(s.size()!=0&&(s.lastElement().mark==2||s.lastElement().mark==3))
                    s.add(new Token("*",5));
                x=new Token(arg.substring(i,t),7);
                i=t-1;flag=true;
            }
            else if(c=='+'||c=='-'){
                x=new Token(String.valueOf(c),4);
                if(s.lastElement().mark==0||s.lastElement().mark==4||s.lastElement().mark==5||s.lastElement().mark==6) {
                    s.add(new Token("0", 2));
                }
            }
            else if(c=='*'||c=='/')
                x=new Token(String.valueOf(c),5);
            else if(c=='^')
                x=new Token(String.valueOf(c),6);
            else if(c=='(')
                x=new Token("(",0);
            else if(c==')')
                x=new Token(")",1);
            if(x!=null){
                s.add(x);
            }
        }
        return s;
    }
    public String toString(){
        return exper.toString();
    }

}
/*
(              0
)              1
十进制数       2
变量           3
//O操作符
+|-            4
*|/            5
^              6
函数           7

 */
/*
            case c:return null;
            case xa:return "a*("+arg+")^(a-1)";
            case sin:return "cos("+arg+")";
            case cos:return "-sin("+arg+")";
            case tan:return "(sec("+arg+"))^2";
            case cot:return "-(csc("+arg+"))^2";
            case sec:return "(sec("+arg+"))*(tan("+arg+"))";
            case csc:return "-(csc("+arg+"))*(cot("+arg+"))";
            case e_x:return "e^("+arg+")";
            case ln:return "("+arg+")^-1";
            case arcsin:return "(1-("+arg+")^2)^-0.5";
            case arccos:return "-(1-("+arg+")^2)^-0.5";
            case arctan:return "(1+("+arg+")^2)^-1";
            case arccot:return "-(1+("+arg+")^2)^-1";
            default:return null;
*/
