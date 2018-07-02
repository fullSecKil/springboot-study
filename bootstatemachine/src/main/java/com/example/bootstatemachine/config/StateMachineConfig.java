package com.example.bootstatemachine.config;

import com.example.bootstatemachine.enums.Events;
import com.example.bootstatemachine.enums.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * 状态机适配类
 */

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        // 定义状态机中的状态
        states
                .withStates()
                    .initial(States.UNPAID)         // 初始状态
                    .states(EnumSet.allOf(States.class));       //指定了使用上一步中定义的所有状态作为该状态机的状态定义
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        // 初始化当前状态机有那些状态迁移动作，
        transitions
                .withExternal()
                    .source(States.UNPAID).target(States.WAITNG_FOR_RECEIVE)    // 指定状态来源和目标
                    .event(Events.PAY)      //  指定触发事件
                    .and()
                .withExternal()
                    .source(States.WAITNG_FOR_RECEIVE).target(States.DONE)
                    .event(Events.RECEIVE);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        // 当前的状态机指定了状态监听器
        config
                .withConfiguration()
                    .listener(listener());         // listener()则是调用了下一个内容创建的监听器实例，用来处理各个各个发生的状态迁移事件
    }

    public StateMachineListener<States, Events> listener(){

        // 状态机监听适配器
        return new StateMachineListenerAdapter<States, Events>(){
            @Override
            public void transition(Transition<States, Events> transition) {
                if(transition.getTarget().getId() == States.UNPAID){
                    logger.info("订单创建,待支付");
                    return;
                }

                if(transition.getSource().getId() == States.UNPAID
                            && transition.getTarget().getId() == States.WAITNG_FOR_RECEIVE){
                    logger.info("用户完成支付，待收货");
                    return;
                }

                if(transition.getSource().getId() == States.WAITNG_FOR_RECEIVE
                        && transition.getTarget().getId() == States.DONE) {
                    logger.info("用户已收货，订单完成");
                    return;
                }
            }
        };
    }
}
