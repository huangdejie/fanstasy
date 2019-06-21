package com.cashbang.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.cashbang.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangdejie
 * @Date: 2019/2/25
 */
public class FirstTest extends BaseTest {

    @Test
    public void testSentinel(){
        initFlowRules();
        while (true){
            Entry entry = null;
            try{
                entry = SphU.entry("hello world");
                System.out.println("hello world");
            }catch (BlockException e){
                System.out.println("blocked");
            }finally {
                if(entry != null){
                    entry.exit();
                }
            }
        }
    }

    private void initFlowRules(){
        List<FlowRule> flowRuleList = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("hello world");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);
        flowRuleList.add(flowRule);
        FlowRuleManager.loadRules(flowRuleList);
    }

}
