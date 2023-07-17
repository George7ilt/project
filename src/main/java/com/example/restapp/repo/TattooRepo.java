package com.example.restapp.repo;

import com.example.restapp.models.Tattoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TattooRepo extends JpaRepository<Tattoo, Long> {
    @Query(nativeQuery = true, value = """
            SELECT master.specialization, AVG(tattoo.price) AS average_price
            FROM master
            INNER JOIN master_tattoo ON Master.id = master_tattoo.master_id
            INNER JOIN Tattoo ON Tattoo.id = master_tattoo.tattoo_id
            GROUP BY Master.specialization
            """
    )
    List<Object[]> getAveragePriceOnSpecializations();

    @Query("SELECT t FROM Tattoo t " +
            "JOIN Master m " +
            "WHERE m.specialization = :specialization " +
            "AND t.price <= :maxPrice AND t.price >= :minPrice")
    List<Tattoo> findTattoosByPriceAndSpecialization(@Param("specialization") String specialization,
                                                     @Param("minPrice") double minPrice,
                                                     @Param("maxPrice") double maxPrice);


    //    Получить все татуировки, которые стоят меньше 5000 рублей и имеют мастера со специализацией "графика".
    @Query(nativeQuery = true, value = """
            SELECT tattoo.id, tattoo.name, tattoo.description, tattoo.price
                FROM tattoo
                INNER JOIN master_tattoo ON tattoo.id = master_tattoo.tattoo_id
                INNER JOIN master ON master.id = master_tattoo.master_id
                WHERE tattoo.price < 5000 AND master.specialization = :spec;
            """)
    List<Tattoo> getCustomTattoosByQuery(@Param("spec") String spec);

}
