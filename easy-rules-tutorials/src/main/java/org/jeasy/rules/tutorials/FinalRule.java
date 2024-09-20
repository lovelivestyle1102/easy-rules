package org.jeasy.rules.tutorials;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

public class FinalRule implements Rule {

    @Override
    public boolean evaluate(Facts facts) {
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
