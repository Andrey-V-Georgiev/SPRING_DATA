package spring_data.car_dealer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.car_dealer.services.SupplierService;

import static spring_data.car_dealer.constants.GlobalConstants.SUPPLIERS_FILE_PATH;

@Controller
public class AppController implements CommandLineRunner {

    private final SupplierService supplierService;

    @Autowired
    public AppController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Seed data */
        this.supplierService.seedSuppliers(SUPPLIERS_FILE_PATH);
        /* Queries */
    }
}
