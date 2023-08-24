package com.appsdeveloperblog.estore.paymentservice.query;

import com.appsdeveloperblog.estore.core.events.PaymentProcessedEvent;
import com.appsdeveloperblog.estore.paymentservice.core.data.PaymentEntity;
import com.appsdeveloperblog.estore.paymentservice.core.data.PaymentsRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
//@ProcessingGroup("payment-group")
public class PaymentEventsHandler {

    Logger logger = LoggerFactory.getLogger(PaymentEventsHandler.class);

    private final PaymentsRepository paymentsRepository;


    public PaymentEventsHandler(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event){
        System.out.println("inside paymentProccessed method===="+event.getOrderId());
        System.out.println("inside paymentProccessed method1===="+event.getPaymentId());
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(event, paymentEntity);
        System.out.println("before saving the payment entity");
        try {
            paymentsRepository.save(paymentEntity);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("after saving the payment entity");
    }
}
