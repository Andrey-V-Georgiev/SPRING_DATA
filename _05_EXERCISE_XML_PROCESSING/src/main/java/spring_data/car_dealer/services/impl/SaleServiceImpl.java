package spring_data.car_dealer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.entities.Sale;
import spring_data.car_dealer.repositories.SaleRepository;
import spring_data.car_dealer.services.CarService;
import spring_data.car_dealer.services.CustomerService;
import spring_data.car_dealer.services.SaleService;
import spring_data.car_dealer.utils.RandomUtil;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final CarService carService;
    private final CustomerService customerService;
    private final SaleRepository saleRepository;
    private final RandomUtil randomUtil;

    @Autowired
    public SaleServiceImpl(CarService carService, CustomerService customerService, SaleRepository saleRepository, RandomUtil randomUtil) {
        this.carService = carService;
        this.customerService = customerService;
        this.saleRepository = saleRepository;
        this.randomUtil = randomUtil;
    }

    @Override
    public void seedSales() {
        /* Discount percentage */
        List<Integer> discounts = Arrays.asList(0, 5, 10, 15, 20, 30, 40, 50);
        /* Seed 20 sales */
        for (int i = 0; i < 30; i++) {
            Sale sale = new Sale();
            sale.setCar(this.carService.getRandomCar());
            sale.setCustomer(this.customerService.getRandomCustomer());
            int randomInt = this.randomUtil.getRandomInt(0, 8);
            int saleDiscount = discounts.get(randomInt);
            sale.setDiscount(saleDiscount);
            this.saleRepository.saveAndFlush(sale);
        }
    }
}
