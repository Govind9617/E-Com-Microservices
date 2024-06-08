package com.grt.order.service;

import com.grt.order.controller.OrderRequest;
import com.grt.order.customerClient.CustomerClient;
import com.grt.order.entity.OrderMapper;
import com.grt.order.entity.OrderResponse;
import com.grt.order.exception.BusinessException;
import com.grt.order.orderline.OrderLineRequest;
import com.grt.order.orderline.OrderLineService;
import com.grt.order.orderproducer.OrderConfirmation;
import com.grt.order.orderproducer.OrderProducer;
import com.grt.order.payment.PaymentClient;
import com.grt.order.payment.PaymentRequest;
import com.grt.order.product.ProductClient;
import com.grt.order.product.PurchaseRequest;
import com.grt.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        var customer = this.customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(() -> new BusinessException("customer id not found"));

        var purchaseProducts = this.productClient.purchaseProduct(orderRequest.products());
        var order = this.orderRepository.save(mapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity()));
        }
        var paymentRequest = new PaymentRequest(orderRequest.amount(),
                orderRequest.paymentMethod(), order.getId(), order.getReference(), customer);

        paymentClient.requestOrderPayment(paymentRequest);
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.orderRepository.findAll().stream().map(mapper::fromOrder).toList();
    }

    public OrderResponse findById(Integer orderId) {
        return this.orderRepository.findById(orderId).map(mapper::fromOrder).
                orElseThrow(() -> new BusinessException("order not found"));
    }
}
