package com.hrovina.onlinestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Optional;

import com.hrovina.onlinestore.entities.*;
import com.hrovina.onlinestore.repositories.OrderItemRepository;
import com.hrovina.onlinestore.repositories.OrderRepository;
import com.hrovina.onlinestore.repositories.PaymentRepository;
import com.hrovina.onlinestore.security.UserSS;
import com.hrovina.onlinestore.services.exceptions.AuthorizationException;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import com.hrovina.onlinestore.enums.PaymentState;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BankBilletService bankBilletService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    public PurchaseOrder search(Integer id) {
        Optional<PurchaseOrder> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + PurchaseOrder.class.getName()));
    }

    @Transactional
    public PurchaseOrder insert(PurchaseOrder obj){
        obj.setId(null);
        obj.setInstant(new Date());
        obj.setClient(clientService.search(obj.getClient().getId()));
        obj.getPayment().setState(PaymentState.PENDING);
        obj.getPayment().setPurchaseOrder(obj);

        if (obj.getPayment() instanceof BankBilletPayment){
            BankBilletPayment billetPayment = (BankBilletPayment) obj.getPayment();
            bankBilletService.fillBankBilletPayment(billetPayment, obj.getInstant());
        }

        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for (OrderItem item : obj.getItemSet()){
            item.setDiscount(0.0);
            item.setProduct(productService.search(item.getProduct().getId()));
            item.setPrice(item.getProduct().getPrice());
            item.setOrderItem(obj);
        }
        orderItemRepository.saveAll(obj.getItemSet());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }

    public Page<PurchaseOrder> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Access Denied");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Client client =  clientService.search(user.getId());
        return repo.findByClient(client, pageRequest);
    }
}
