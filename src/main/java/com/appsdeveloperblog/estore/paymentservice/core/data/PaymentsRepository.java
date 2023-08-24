package com.appsdeveloperblog.estore.paymentservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<PaymentEntity, String> {
    PaymentEntity findByPaymentId(String paymentId);
}
