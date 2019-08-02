package com.example.flowableboot.listeners;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StartOtherProcessListener implements ExecutionListener {

    private final RuntimeService runtimeService;

    public StartOtherProcessListener(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void notify(DelegateExecution delegateExecution) {
        runtimeService.startProcessInstanceByKey("otherProcess");
    }
}
