package spring_data.car_dealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.dtos.exportdtos.CustomerExportDto;
import spring_data.car_dealer.models.dtos.exportdtos.CustomerExportDto2;
import spring_data.car_dealer.models.dtos.exportdtos.CustomersExportRootDto;
import spring_data.car_dealer.models.dtos.exportdtos.CustomersExportRootDto2;
import spring_data.car_dealer.models.dtos.importdtos.CustomerSeedDto;
import spring_data.car_dealer.models.dtos.importdtos.CustomerSeedRootDto;
import spring_data.car_dealer.models.entities.BaseEntity;
import spring_data.car_dealer.models.entities.Car;
import spring_data.car_dealer.models.entities.Customer;
import spring_data.car_dealer.models.entities.Part;
import spring_data.car_dealer.repositories.CarRepository;
import spring_data.car_dealer.repositories.CustomerRepository;
import spring_data.car_dealer.repositories.PartRepository;
import spring_data.car_dealer.services.CustomerService;
import spring_data.car_dealer.services.RandomService;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final RandomService randomService;
    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CustomerServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, CustomerRepository customerRepository, RandomService randomService, CarRepository carRepository, PartRepository partRepository) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.randomService = randomService;
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }

    @Override
    public void seedCustomers(String filePath) throws JAXBException, FileNotFoundException {
        /* Parse the XMLs to Dtos */
        CustomerSeedRootDto customerSeedRootDto = this.xmlParser.unmarshalFromFile(filePath, CustomerSeedRootDto.class);
        /* Validate the Dtos */
        if (this.validationUtil.isValid(customerSeedRootDto)) {
            /* Get the parts from root Dto */
            List<CustomerSeedDto> customerDtos = customerSeedRootDto.getCustomers();
            for (CustomerSeedDto c : customerDtos) {
                if (this.validationUtil.isValid(c)) {
                    /* Check in DB for already existing entity */
                    Optional<Customer> customerOptional = this.customerRepository.findCustomer(c.getName(), c.getBirthDate(), c.isYoungDriver());
                    if (customerOptional.isEmpty()) {
                        /* If there is no occurrence save */
                        Customer customer = this.modelMapper.map(c, Customer.class);
                        this.customerRepository.saveAndFlush(customer);
                    } else {
                        System.out.println("Customer already exists in DB");
                    }
                } else {
                    this.validationUtil.printConstraintViolations(c);
                }
            }
        } else {
            this.validationUtil.printConstraintViolations(customerSeedRootDto);
        }
    }

    @Override
    public Customer getRandomCustomer() {
        int repoCount = (int) this.customerRepository.count();
        Customer randomCustomer = this.randomService.getRandomInstance(
                repoCount, Customer.class, this.customerRepository
        );
        return randomCustomer;
    }

    @Override
    public void exportOrderedCustomers(String filePath) throws JAXBException {
        List<Customer> customers = this.customerRepository.findCustomersAllOrderByBirthDateTHenByYoungDriver();
        List<CustomerExportDto> customerExportDtos = customers
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerExportDto.class))
                .collect(Collectors.toList());
        CustomersExportRootDto customersExportRootDto = new CustomersExportRootDto();
        customersExportRootDto.setCustomers(customerExportDtos);
        this.xmlParser.marshalToFile(filePath, customersExportRootDto);
    }

    @Override
    public void exportTotalSalesByCustomer(String filePath) throws JAXBException {
        List<CustomerExportDto2> customerExportDtos2 = this.customerRepository.findCustomersBoughtAtLeastOneCar();
        for (CustomerExportDto2 c : customerExportDtos2) {
            List<Car> carsBoughtByCustomer = this.carRepository.findCarsBoughtByCustomer(c.getId());
            c.setBoughtCars((long) carsBoughtByCustomer.size());
            List<Long> carIds = carsBoughtByCustomer
                    .stream()
                    .map(BaseEntity::getId)
                    .collect(Collectors.toList());
            List<Part> partsBoughtByCustomer = this.partRepository.findPartsByArrOfCarIds(carIds);
            BigDecimal totalMoney = partsBoughtByCustomer
                    .stream()
                    .map(Part::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            c.setSpentMoney(totalMoney);
        }

        CustomersExportRootDto2 customersExportRootDto2 = new CustomersExportRootDto2();
        customersExportRootDto2.setCustomers(customerExportDtos2);
        this.xmlParser.marshalToFile(filePath, customersExportRootDto2);

    }
}
