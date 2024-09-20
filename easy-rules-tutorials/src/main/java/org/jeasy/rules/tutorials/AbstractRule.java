package org.jeasy.rules.tutorials;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.List;

public abstract class AbstractRule implements Rule {
     private List<Condition> conditions;

     private AbstractAction action;

     public List<Condition> getConditions() {
          return conditions;
     }

     public void setConditions(List<Condition> conditions) {
          this.conditions = conditions;
     }

     public AbstractAction getAction() {
          return action;
     }

     public void setAction(AbstractAction action) {
          this.action = action;
     }
}
