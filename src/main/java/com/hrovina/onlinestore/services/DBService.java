package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.*;
import com.hrovina.onlinestore.repositories.*;
import com.hrovina.onlinestore.enums.ClientType;
import com.hrovina.onlinestore.enums.PaymentState;
import com.hrovina.onlinestore.enums.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
       Product p11 = new Product(null, "Product", 90.00);
       Product p12 = new Product(null, "Product", 90.00);
       Product p13 = new Product(null, "Product", 90.00);
       Product p14 = new Product(null, "Product", 90.00);
       Product p15 = new Product(null, "Product", 90.00);
       Product p16 = new Product(null, "Product", 90.00);
       Product p17 = new Product(null, "Product", 90.00);
       Product p18 = new Product(null, "Product", 90.00);
       Product p19 = new Product(null, "Product", 90.00);
       Product p20 = new Product(null, "Product", 90.00);
       Product p21 = new Product(null, "Product", 90.00);
       Product p22 = new Product(null, "Product", 90.00);
       Product p23 = new Product(null, "Product", 90.00);
       Product p24 = new Product(null, "Product", 90.00);
       Product p25 = new Product(null, "Product", 90.00);
       Product p26 = new Product(null, "Product", 90.00);
       Product p27 = new Product(null, "Product", 90.00);
       Product p28 = new Product(null, "Product", 90.00);
       Product p29 = new Product(null, "Product", 90.00);
       Product p30 = new Product(null, "Pending", 180.00);
       Product p31 = new Product(null, "Product", 90.00);
       Product p32 = new Product(null, "Product", 90.00);
       Product p33 = new Product(null, "Product", 90.00);
       Product p34 = new Product(null, "Product", 90.00);
       Product p35 = new Product(null, "Product", 90.00);
       Product p36 = new Product(null, "Product", 90.00);
       Product p37 = new Product(null, "Product", 90.00);
       Product p38 = new Product(null, "Product", 90.00);
       Product p39 = new Product(null, "Product", 90.00);
       Product p40 = new Product(null, "Product", 90.00);
       Product p41 = new Product(null, "Product", 90.00);
       Product p42 = new Product(null, "Product", 90.00);
       Product p43 = new Product(null, "Product", 90.00);
       Product p44 = new Product(null, "Product", 90.00);
       Product p45 = new Product(null, "Product", 90.00);
       Product p46 = new Product(null, "Product", 90.00);
       Product p47 = new Product(null, "Product", 90.00);
       Product p48 = new Product(null, "Product", 90.00);
       Product p49 = new Product(null, "Product", 90.00);
       Product p50 = new Product(null, "Product", 90.00);

       cat1.getProductList().addAll(Arrays.asList(p1, p2, p3, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22,
                                                    p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35,
                                                    p36,p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49,
                                                    p50));
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
       p12.getCategoryList().add(cat1);
       p13.getCategoryList().add(cat1);
       p14.getCategoryList().add(cat1);
       p15.getCategoryList().add(cat1);
       p16.getCategoryList().add(cat1);
       p17.getCategoryList().add(cat1);
       p18.getCategoryList().add(cat1);
       p19.getCategoryList().add(cat1);
       p20.getCategoryList().add(cat1);
       p21.getCategoryList().add(cat1);
       p22.getCategoryList().add(cat1);
       p23.getCategoryList().add(cat1);
       p24.getCategoryList().add(cat1);
       p25.getCategoryList().add(cat1);
       p26.getCategoryList().add(cat1);
       p27.getCategoryList().add(cat1);
       p28.getCategoryList().add(cat1);
       p29.getCategoryList().add(cat1);
       p30.getCategoryList().add(cat1);
       p31.getCategoryList().add(cat1);
       p32.getCategoryList().add(cat1);
       p33.getCategoryList().add(cat1);
       p34.getCategoryList().add(cat1);
       p35.getCategoryList().add(cat1);
       p36.getCategoryList().add(cat1);
       p37.getCategoryList().add(cat1);
       p38.getCategoryList().add(cat1);
       p39.getCategoryList().add(cat1);
       p40.getCategoryList().add(cat1);
       p41.getCategoryList().add(cat1);
       p42.getCategoryList().add(cat1);
       p43.getCategoryList().add(cat1);
       p44.getCategoryList().add(cat1);
       p45.getCategoryList().add(cat1);
       p46.getCategoryList().add(cat1);
       p47.getCategoryList().add(cat1);
       p48.getCategoryList().add(cat1);
       p49.getCategoryList().add(cat1);
       p50.getCategoryList().add(cat1);
       

       categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
       productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16,
                                        p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32,
                                        p33, p34, p35, p36,p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
                                        p49, p50));

       State est1 = new State(null, "ON");
       State est2 = new State(null, "AL");

       City c1  = new City(null, "Toronto", est1);
       City c2  = new City(null, "Markham", est2);
       City c3  = new City(null, "Campinas", est2);

       est1.getCityList().addAll(Arrays.asList(c2,c3));
       est2.getCityList().add(c1);

       stateRepository.saveAll(Arrays.asList(est1, est2));
       cityRepository.saveAll(Arrays.asList(c1, c2, c3));

       Client client1 = new Client(null, "Maria S", "humbertorovina@hotmail.com", "3554634", ClientType.LEGAL_PERSON, bCryptPasswordEncoder.encode("123"));
       client1.getPhones().addAll(Arrays.asList("6479160000","6479161111"));

       Address add1 = new Address(null, "Eglinton Ave", "200", "Apt. 109", "YE", "m9v9l9", client1, c1);
       Address add2 = new Address(null, "Spadina Ave", "60", "Apt. 214", "Spadina", "m9v9l9", client1, c2);

       client1.getAddressList().addAll(Arrays.asList(add1, add2));

       Client client2 = new Client(null, "Berto R", "humbertorovina@gmail.com", "4141421", ClientType.LEGAL_PERSON, bCryptPasswordEncoder.encode("123"));
       client2.addProfiles(Profile.ADMIN);
       client2.getPhones().addAll(Arrays.asList("6479165555","6479162222"));

       Address add3 = new Address(null, "Spadina Rd St", "90", null, "Spadina", "m9v9l9", client2, c1);
       Address add4 = new Address(null, "Twenty 1st Ave", "123", "Apt. 5", "Etobicoke", "m9v9l9", client2, c1);
       Address add5 = new Address(null, "Yonge Ave", "863", "Apt. 34", "YE", "m9v9l9", client2, c1);

       client2.getAddressList().addAll(Arrays.asList(add3, add4, add5));

       clientRepository.save(client1);
       clientRepository.save(client2);
       addressRepository.saveAll(Arrays.asList(add1, add2));
       addressRepository.saveAll(Arrays.asList(add3, add4, add5));

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
