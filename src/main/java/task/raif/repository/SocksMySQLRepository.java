package task.raif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import task.raif.model.SocksStorage;

import java.util.Optional;

@Repository
public interface SocksMySQLRepository extends JpaRepository<SocksStorage, Long> {

    public Optional<SocksStorage> getByColorAndCottonPart(String color, int cottonPart);

    @Query("select sum(quantity) from SocksStorage "
            + "where (:color = color) and (:cottonPart > cottonPart)")
    public Optional<Integer>getQuantityByColorAndCottonPartLessThen(String color, int cottonPart);

    @Query("select sum(quantity) from SocksStorage "
            + "where (:color = color) and (:cottonPart < cottonPart)")
    public Optional<Integer> getQuantityByColorAndCottonPartMoreThen(String color, int cottonPart);

}
