package com.example.restapp.repo;

import com.example.restapp.models.Tattoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TattooRepo extends JpaRepository<Tattoo, Long> {
    @Query(value = """
            SELECT m.specialization, AVG(t.price) AS average_price
            FROM Master m
            INNER JOIN MastersTattoos mt ON m.id = mt.master.id
            INNER JOIN Tattoo t ON t.id = mt.tattoo.id
            GROUP BY m.specialization
            """
    )
    List<Object[]> getAveragePriceOnSpecializations();

    @Query(value = "SELECT distinct t FROM Tattoo t " +
            "INNER JOIN MastersTattoos mt ON t.id = mt.tattoo.id " +
            "INNER JOIN Master m ON mt.master.id = m.id " +
            "WHERE m.specialization = :specialization " +
            "AND t.price <= :maxPrice " +
            "AND t.price >= :minPrice")
    List<Tattoo> findTattoosByPriceAndSpecialization(@Param("specialization") String specialization,
                                                     @Param("minPrice") double minPrice,
                                                     @Param("maxPrice") double maxPrice);


    //    Получить все татуировки, которые стоят меньше 5000 рублей и имеют мастера со специализацией "графика".
    @Query(value = "SELECT t "+
                " FROM Tattoo t " +
                " INNER JOIN MastersTattoos mt ON t.id = mt.tattoo.id "+
                " INNER JOIN Master m ON m.id = mt.master.id "+
                " WHERE t.price < 5000 AND m.specialization = :spec")
    List<Tattoo> getCustomTattoosByQuery(@Param("spec") String spec);

}
