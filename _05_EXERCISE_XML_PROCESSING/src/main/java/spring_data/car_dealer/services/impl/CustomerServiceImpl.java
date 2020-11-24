package spring_data.car_dealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.dtos.CustomerSeedDto;
import spring_data.car_dealer.models.dtos.CustomerSeedRootDto;
import spring_data.car_dealer.models.entities.Car;
import spring_data.car_dealer.models.entities.Customer;
import spring_data.car_dealer.repositories.CustomerRepository;
import spring_data.car_dealer.services.CustomerService;
import spring_data.car_dealer.services.RandomService;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final RandomService randomService;

    @Autowired
    public CustomerServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, CustomerRepository customerRepository, RandomService randomService) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.randomService = randomService;
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
                repoCount,  Customer.class, this.customerRepository
        );
        return randomCustomer;
    }
}
