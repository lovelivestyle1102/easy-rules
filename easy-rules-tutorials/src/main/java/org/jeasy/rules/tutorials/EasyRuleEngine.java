package org.jeasy.rules.tutorials;

import com.google.inject.Injector;
import org.jeasy.rules.api.*;
import org.jeasy.rules.core.AbstractRulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class EasyRuleEngine extends AbstractRulesEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(InferenceRulesEngine.class);

    private static volatile DefaultRulesEngine delegate = new DefaultRulesEngine(new RulesEngineParameters());

    private static  volatile Map<String, Injector> coreInjector = new HashMap(20);

    private static ReentrantLock reentrantLock = new ReentrantLock();

    protected EasyRuleEngine(RulesEngineParameters parameters) {
        super(parameters);
    }

    @Override
    public void fire(Rules rules, Facts facts) {
        Objects.requireNonNull(rules, "Rules must not be null");
        Objects.requireNonNull(facts, "Facts must not be null");
        Set<Rule> selectedRules;
        do {
            LOGGER.debug("Selecting candidate rules based on the following facts: {}", facts);
            selectedRules = selectCandidates(rules, facts);
            if (!selectedRules.isEmpty()) {
                delegate.fire(new Rules(selectedRules), facts);
            } else {
                LOGGER.debug("No candidate rules found for facts: {}", facts);
            }
        } while (!selectedRules.isEmpty());
    }

    public static void fireTemplateRule(String templateId,Facts facts){
        Injector injector = coreInjector.get(templateId);

        if(injector == null){
            createTemplateInjector(templateId);
        }

//        fireRule((Rules) injector.getBindings().values(),facts);
    }

    public static void fireRule(Rules rules, Facts facts){
        Objects.requireNonNull(rules, "Rules must not be null");
        Objects.requireNonNull(facts, "Facts must not be null");
        Set<Rule> selectedRules;
        do {
            LOGGER.debug("Selecting candidate rules based on the following facts: {}", facts);
            selectedRules = selectCandidates(rules, facts);
            if (!selectedRules.isEmpty()) {
                delegate.fire(new Rules(selectedRules), facts);
            } else {
                LOGGER.debug("No candidate rules found for facts: {}", facts);
            }
        } while (!selectedRules.isEmpty());
    }

    private static void createTemplateInjector(String templateId) {
        reentrantLock.tryLock();
        try{

        }finally {
            reentrantLock.unlock();
        }
    }

    private static Set<Rule> selectCandidates(Rules rules, Facts facts) {
        Set<Rule> candidates = new TreeSet<>();
        for (Rule rule : rules) {
            if (rule.evaluate(facts)) {
                candidates.add(rule);
            }
        }
        return candidates;
    }

    @Override
    public Map<Rule, Boolean> check(Rules rules, Facts facts) {
        Objects.requireNonNull(rules, "Rules must not be null");
        Objects.requireNonNull(facts, "Facts must not be null");
        return delegate.check(rules, facts);
    }

    /**
     * Register a rule listener.
     * @param ruleListener to register
     */
    @Override
    public void registerRuleListener(RuleListener ruleListener) {
        super.registerRuleListener(ruleListener);
        delegate.registerRuleListener(ruleListener);
    }

    /**
     * Register a list of rule listener.
     * @param ruleListeners to register
     */
    @Override
    public void registerRuleListeners(List<RuleListener> ruleListeners) {
        super.registerRuleListeners(ruleListeners);
        delegate.registerRuleListeners(ruleListeners);
    }

    /**
     * Register a rules engine listener.
     * @param rulesEngineListener to register
     */
    @Override
    public void registerRulesEngineListener(RulesEngineListener rulesEngineListener) {
        super.registerRulesEngineListener(rulesEngineListener);
        delegate.registerRulesEngineListener(rulesEngineListener);
    }

    /**
     * Register a list of rules engine listener.
     * @param rulesEngineListeners to register
     */
    @Override
    public void registerRulesEngineListeners(List<RulesEngineListener> rulesEngineListeners) {
        super.registerRulesEngineListeners(rulesEngineListeners);
        delegate.registerRulesEngineListeners(rulesEngineListeners);
    }
}
