package spring_data.car_dealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.car_dealer.constants.*;
import spring_data.car_dealer.services.*;

@Controller
public class AppController implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    @Autowired
    public AppController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Seed data */
//        this.supplierService.seedSuppliers(GlobalConstants.SUPPLIERS_FILE_PATH);
//        this.partService.seedParts(GlobalConstants.PARTS_FILE_PATH);
//        this.carService.seedCars(GlobalConstants.CARS_FILE_PATH);
//        this.customerService.seedCustomers(GlobalConstants.CUSTOMERS_FILE_PATH);
//        this.saleService.seedSales();

        /* Queries */
        //this.customerService.exportOrderedCustomers(GlobalConstants.QUERY_1_FILE_PATH);
        //this.carService.exportCarsFromMake(GlobalConstants.QUERY_2_FILE_PATH, "Toyota");
        //this.supplierService.exportLocalSuppliers(GlobalConstants.QUERY_3_FILE_PATH);
        this.carService.exportCarsWithTheirListOfParts(GlobalConstants.QUERY_4_FILE_PATH);
    }
}
