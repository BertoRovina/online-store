package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.*;
import com.hrovina.onlinestore.repositories.*;
import enums.ClientType;
import enums.PaymentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

   public void instantiateTestDatabase() throws ParseException {
       Category cat1 = new Category(null, "IT");
       Category cat2 = new Category(null, "Office");
       Category cat3 = new Category(null, "House utilities");
       Category cat4 = new Category(null, "Electronics");
       Category cat5 = new Category(null, "Garden");
       Category cat6 = new Category(null, "Decoration");
       Category cat7 = new Category(null, "Essence");

       Product p1 = new Product(null, "Computer", 2000.00);
       Product p2 = new Product(null, "Printer", 800.00);
       Product p3 = new Product(null, "Mouse", 80.00);
       Product p4 = new Product(null, "Office Desk", 300.00);
       Product p5 = new Product(null, "Towel", 50.00);
       Product p6 = new Product(null, "Quilt", 200.00);
       Product p7 = new Product(null, "TV true color", 1200.00);
       Product p8 = new Product(null, "Trimmer", 800.00);
       Product p9 = new Product(null, "Bedside lamp", 100.00);
       Product p10 = new Product(null, "Pending", 180.00);
       Product p11 = new Product(null, "Shampoo", 90.00);

       cat1.getProductList().addAll(Arrays.asList(p1, p2, p3));
       cat2.getProductList().add(p2);

       p1.getCategoryList().add(cat1);
       p2.getCategoryList().addAll(Arrays.asList(cat1, cat2));
       p3.getCategoryList().add(cat1);
       cat2.getProductList().addAll(Arrays.asList(p2, p4));
       cat3.getProductList().addAll(Arrays.asList(p5, p6));
       cat4.getProductList().addAll(Arrays.asList(p1, p2, p3, p7));
       cat5.getProductList().add(p8);
       cat6.getProductList().addAll(Arrays.asList(p9, p10));
       cat7.getProductList().add(p11);

       p1.getCategoryList().addAll(Arrays.asList(cat1, cat4));
       p2.getCategoryList().addAll(Arrays.asList(cat1, cat2, cat4));
       p3.getCategoryList().addAll(Arrays.asList(cat1, cat4));
       p4.getCategoryList().add(cat2);
       p5.getCategoryList().add(cat3);
       p6.getCategoryList().add(cat3);
       p7.getCategoryList().add(cat4);
       p8.getCategoryList().add(cat5);
       p9.getCategoryList().add(cat6);
       p10.getCategoryList().add(cat6);
       p11.getCategoryList().add(cat7);

       categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
       productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

       State est1 = new State(null, "SP");
       State est2 = new State(null, "MG");

       City c1  = new City(null, "Uberlandia", est1);
       City c2  = new City(null, "Sao Paulo", est2);
       City c3  = new City(null, "Campinas", est2);

       est1.getCityList().addAll(Arrays.asList(c2,c3));
       est2.getCityList().add(c1);

       stateRepository.saveAll(Arrays.asList(est1, est2));
       cityRepository.saveAll(Arrays.asList(c1, c2, c3));

       Client client1 = new Client(null, "Maria S", "maria@gmail.com", "3554634",
               ClientType.LEGAL_PERSON);
       client1.getPhones().addAll(Arrays.asList("6479160000","6479161111"));

       Address add1 = new Address(null, "Eglinton Ave", "200", "Apt. 109", "YE", "m9v9l9", client1, c1);
       Address add2 = new Address(null, "Spadina Ave", "60", "Apt. 214", "Spadina", "m9v9l9", client1, c2);

       client1.getAddressList().addAll(Arrays.asList(add1, add2));

       clientRepository.save(client1);
       addressRepository.saveAll(Arrays.asList(add1, add2));

       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/DD/yyyy HH:mm");
       PurchaseOrder order1 = new PurchaseOrder(null, simpleDateFormat.parse("09/30/2017 10:32"), client1, add1);
       PurchaseOrder order2 = new PurchaseOrder(null, simpleDateFormat.parse("09/30/2017 19:35"), client1, add2);

       Payment payment1 = new CardPayment(null, PaymentState.PAID, order1, 6);
       order1.setPayment(payment1);

       Payment payment2 = new BankBilletPayment(null, PaymentState.PENDING, order2, simpleDateFormat.parse("10/20/2017 00:00"), null);
       order2.setPayment(payment2);

       client1.getPurchaseOrderList().addAll(Arrays.asList(order1, order2));

       orderRepository.saveAll(Arrays.asList(order1, order2));
       paymentRepository.saveAll(Arrays.asList(payment1, payment2));

       OrderItem orderItem1 = new OrderItem(order1, p1, 0.0, 1, 2000.0);
       OrderItem orderItem2 = new OrderItem(order1, p3, 0.0, 2, 80.0);
       OrderItem orderItem3 = new OrderItem(order2, p2, 100.0, 1, 800.0);

       order1.getItemSet().addAll(Arrays.asList(orderItem1, orderItem2));
       order2.getItemSet().add(orderItem3);

       p1.getItemSet().add(orderItem1);
       p1.getItemSet().add(orderItem3);
       p1.getItemSet().add(orderItem2);

       orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
   }
}
