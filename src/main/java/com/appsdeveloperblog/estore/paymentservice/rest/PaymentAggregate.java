package com.appsdeveloperblog.estore.paymentservice.rest;

import com.appsdeveloperblog.estore.core.commands.ProcessPaymentCommand;

import com.appsdeveloperblog.estore.core.events.PaymentProcessedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Aggregate
public class PaymentAggregate {
    Logger logger = LoggerFactory.getLogger(PaymentAggregate.class);

    @AggregateIdentifier
    private String paymentId;
    private String orderId;


    public PaymentAggregate() {
    }

    @CommandHandler
    public PaymentAggregate(ProcessPaymentCommand processPaymentCommand) throws Exception{
        logger.info("inside PaymentAggregate============");
        PaymentProcessedEvent paymentProcessedEvent = new PaymentProcessedEvent();
        BeanUtils.copyProperties(processPaymentCommand, paymentProcessedEvent);
        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent){
        logger.info("PaymentProcessedEvent has been triggerred from PaymentAggregate class");
        this.orderId = paymentProcessedEvent.getOrderId();
        this.paymentId = paymentProcessedEvent.getPaymentId();

    }
}
