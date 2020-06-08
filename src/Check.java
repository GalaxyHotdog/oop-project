import java.util.Stack;
import java.util.Vector;

public class Check {
    public Check(Vector tokens)throws Exception{

        GrammarCheck check1=new GrammarCheck(tokens);
        ParenthesesCheck check2=new ParenthesesCheck(tokens);
    }
    class ParenthesesCheck
    {
        private boolean isOpenParenthesis( String c )
        {
            if ( c.equals("("))
                return true;
            else
                return false;
        }
        private boolean isClosedParenthesis( String c )
        {
            if ( c.equals(")"))
                return true;
            else
                return false;
        }
        private boolean parenthesesMatch( String open, String closed )
        {
            if ( open.equals("(") && closed.equals(")") )
                return true;
            else
                return false;
        }
        private ParenthesesCheck(Vector tokens) throws Exception {
            Stack<String> s = new Stack();
            int         i;
            String      currentChar;
            String      c;
            for ( i=0; i < tokens.size(); i++ )
            {
                currentChar= (String) ((Token) tokens.elementAt(i)).token;
                if ( isOpenParenthesis( currentChar ) )
                {
                    s.push( currentChar );
                }
                else if ( isClosedParenthesis( currentChar ) )
                {
                    if ( s.isEmpty() )
                    {
                         throw new Exception();
                    }
                    else
                    {
                        c=s.pop();
                        if ( !parenthesesMatch( c, currentChar ) )
                        {
                            throw new Exception();
                        }
                    }
                }
            }
            if ( !s.isEmpty() )
                throw new Exception();
        }
    }
    class GrammarCheck{
        boolean[][] seq = new boolean[8][8];
        {
            seq[0][0]=seq[0][2]=seq[0][3]=seq[0][7]=true;seq[0][1]=seq[0][4]=seq[0][5]=seq[0][6]=false;
            seq[1][0]=seq[1][1]=seq[1][4]=seq[1][5]=seq[1][6]=true;seq[1][2]=seq[1][3]=seq[1][7]=false;
            seq[2][1]=seq[2][2]=seq[2][3]=seq[2][4]=seq[2][5]=seq[2][6]=seq[2][7]=true;seq[2][0]=false;
            seq[3][1]=seq[3][3]=seq[3][4]=seq[3][5]=seq[3][6]=true;seq[3][0]=seq[3][2]=seq[3][7]=false;
            seq[4][0]=seq[4][2]=seq[4][3]=seq[4][7]=true;seq[4][1]=seq[4][4]=seq[4][5]=seq[4][6]=false;
            seq[5][0]=seq[5][2]=seq[5][3]=seq[5][7]=true;seq[5][1]=seq[5][4]=seq[5][5]=seq[5][6]=false;
            seq[6][0]=seq[6][2]=seq[6][3]=seq[6][7]=true;seq[6][1]=seq[6][4]=seq[6][5]=seq[6][6]=false;
            seq[7][0]=seq[7][1]=seq[7][4]=seq[7][5]=seq[7][6]=true;seq[7][2]=seq[7][3]=seq[7][7]=false;
            
        }
        private void beginCheck( Vector tokens) throws Exception
        {
            int      mark;
            Token    t;
            t=(Token)tokens.elementAt( 0 );
            mark=t.mark;
            if (mark == 1|| mark == 4||mark == 5||mark ==6) {
                throw new Exception();
            }
        }
        private void endCheck( Vector tokens) throws Exception
        {
            int      mark;
            Token    t;
            t=(Token)tokens.elementAt( tokens.size()-1 );
            mark=t.mark;
            if (mark==0||mark == 4||mark == 5||mark ==6)
                throw new Exception();
        }
        private void sequenceCheck(Vector tokens) throws Exception
        {
            for (int i = 0; i < tokens.size()-1; i++){
                Token x=(Token)tokens.elementAt(i);
                Token y=(Token)tokens.elementAt(i+1);
                if(seq[x.mark][y.mark]==false)
                    throw new Exception();
            }
        }
        private GrammarCheck(Vector tokens) throws Exception{
            beginCheck(tokens);
            endCheck(tokens);
            sequenceCheck(tokens);
        }
    }

}
