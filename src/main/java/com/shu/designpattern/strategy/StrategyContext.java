package com.shu.designpattern.strategy;

import javax.sound.midi.VoiceStatus;

/**
 * @author shuxibing
 * @date 2019/8/6 11:11
 * @uint d9lab
 * @Description:
 */
public class StrategyContext {
    private  Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy=strategy;
    }
    public String doAnything(){
        return strategy.doSomething();
    }
}
