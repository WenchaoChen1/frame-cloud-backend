package com.frame.template.service.system.controller;

import com.stripe.*;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

public class StripeController {

    public static void main(String[] args) throws StripeException {
        Stripe.apiKey = "sk_test_51PNQG8Hsb9kAuba1PyOGdmhpyuR17Y0Gqq3OrjWzYxQSeFrfZyVO33akMLPZMA9PmQhsHSkyHcN4PSE3qZwFAgF700dRCVuq76";

        SessionCreateParams params =
            SessionCreateParams.builder()
                .setSuccessUrl("https://example.com/success")
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setPrice("price_1MotwRLkdIwHu7ixYcPLm5uZ")
                        .setQuantity(2L)
                        .build()
                )
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .build();

        Session session = Session.create(params);
        System.out.println("Hello, World!");
    }
}