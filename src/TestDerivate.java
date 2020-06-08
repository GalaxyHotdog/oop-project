import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

public class TestDerivate {
    enum Cal{
        c,xa,sin,cos,tan,cot,sec,csc,e_x,a_x,ln,log,arcsin,arccos,arctan,arccot
    }
    public static void main(String[] args) throws Exception {
        String arg="sin(-2x)";
        Vector<Token> s=ToToken(arg);
        System.out.println(s);
        try{
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
            Token exper=s.elementAt(0);
            System.out.println(exper.derivate().value(60));
            System.out.println(f(exper.derivate()));
        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public static String f(Token token){
        if(token.mark!=-1){
            return token.token;
        }
        if(token.left==null&&token.right!=null){
            return token.operator.token+"("+token.right+")";
        }
        else {
            String s="";
            if(token.left.operator!=null&&token.left.operator.mark<token.operator.mark){
                s+="("+f(token.left)+")";
            }
            else
                s+=f(token.left);
            s+=token.operator.token;
            if(token.right.operator!=null&&token.right.operator.mark<token.operator.mark){
                s+="("+f(token.right)+")";
            }
            else
                s+=f(token.right);
            return s;
        }
    }
    public static Vector<Token> ToToken(String arg){
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
