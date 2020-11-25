package spring_data.car_dealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.dtos.exportdtos.SupplierExportDto;
import spring_data.car_dealer.models.entities.Supplier;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findSupplierByName(String name);

    @Query(value = " SELECT new spring_data.car_dealer.models.dtos.exportdtos.SupplierExportDto(s.id, s.name, count(p.id)) " +
            " FROM Part AS p " +
            " JOIN p.supplier AS s " +
            " WHERE s.importer = false" +
            " GROUP BY s.id")
    List<SupplierExportDto>  findSuppliersNotImporters();
}
