package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.xml_dtos.TicketSeedDto;
import softuni.exam.models.dtos.xml_dtos.TicketSeedRootDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TicketRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.PlaneService;
import softuni.exam.service.TicketService;
import softuni.exam.service.TownService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;
import softuni.exam.utils.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final TicketRepository ticketRepository;
    private final TownService townService;
    private final PassengerService passengerService;
    private final PlaneService planeService;

    @Autowired
    public TicketServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, TicketRepository ticketRepository, TownService townService, PassengerService passengerService, PlaneService planeService) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.ticketRepository = ticketRepository;
        this.townService = townService;
        this.passengerService = passengerService;
        this.planeService = planeService;
    }

    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.TICKETS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the XMLs to Dtos */
        TicketSeedRootDto rootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.TICKETS_INPUT_PATH, TicketSeedRootDto.class);

        /* Validate the Dtos */
        List<TicketSeedDto> dtos = rootDto.getTickets();
        for (TicketSeedDto dto : dtos) {
            if (this.validationUtil.isValid(dto)) {

                /* Prevent duplicates */
                Optional<Ticket> ticketOptional = this.ticketRepository
                        .findTicketBySerialNumber(dto.getSerialNumber());

                if (ticketOptional.isEmpty()) {
                    Ticket ticket = this.modelMapper.map(dto, Ticket.class);

                    /* get town from */
                    Town townFrom = this.townService.findTownByName(dto.getFromTown().getName());
                    ticket.setFromTown(townFrom);

                    /* get town to */
                    Town townTo = this.townService.findTownByName(dto.getToTown().getName());
                    ticket.setToTown(townTo);

                    /* get passenger */
                    Passenger passengerByEmail = this.passengerService
                            .findPassengerByEmail(dto.getPassenger().getEmail());
                    ticket.setPassenger(passengerByEmail);

                    /* get plane */
                    Plane planeByRegisterNumber = this.planeService
                            .findPlaneByRegisterNumber(dto.getPlane().getRegisterNumber());
                    ticket.setPlane(planeByRegisterNumber);

                    this.ticketRepository.saveAndFlush(ticket);
                    sb.append(String.format(
                            "Successfully imported plane with serial number - %s\n", dto.getSerialNumber()));
                }
            } else {
                sb.append("Invalid ticket\n");
            }
        }
        return sb.toString();
    }
}
