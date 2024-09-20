package org.jeasy.rules.tutorials;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

public class OR extends PostfixMathCommand {

    public OR(){
        super();
        numberOfParameters = 2;
    }

    @Override
    public void run(Stack stack) throws ParseException {
        checkStack(stack);

        String param2 = (String)stack.pop();

        String param1 = (String)stack.pop();


    }
}
