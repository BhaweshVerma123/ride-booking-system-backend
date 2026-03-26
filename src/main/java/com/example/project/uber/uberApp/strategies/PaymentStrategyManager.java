package com.example.project.uber.uberApp.strategies;

import com.example.project.uber.uberApp.entities.enums.PaymentMethod;
import com.example.project.uber.uberApp.strategies.Impl.CashPaymentStrategy;
import com.example.project.uber.uberApp.strategies.Impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.project.uber.uberApp.entities.enums.PaymentMethod.CASH;
import static com.example.project.uber.uberApp.entities.enums.PaymentMethod.WALLET;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
       return  switch(paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;

        };
    }


}
