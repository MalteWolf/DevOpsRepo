package encobib.repository;

import java.util.List;

import encobib.model.LendingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGreSqlLendingPeriodsRepository extends CrudRepository<LendingPeriod, Integer> {

    List<LendingPeriod> findLendingPeriodsByBookId(int id);
}
