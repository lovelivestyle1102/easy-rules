package org.jeasy.rules.tutorials;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELCondition;

import java.util.List;

public class TemplateRule extends AbstractRule{

    public TemplateRule(List<String> rules){
        for(String rule: rules){
            MVELCondition mvelCondition = new MVELCondition(rule);
            this.getConditions().add(mvelCondition);
        }
    }

    @Override
    public boolean evaluate(Facts facts) {
        boolean result = false;
        List<Condition> conditions = this.getConditions();
        for(Condition condition: conditions){
            result = condition.evaluate(facts);
            if(result == true){
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Facts facts) throws Exception {

    }

    @Override
    public int compareTo(Rule o) {
        return 0;
    }
}
