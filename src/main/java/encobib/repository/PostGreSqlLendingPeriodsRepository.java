package encobib.repository;

import java.util.List;

import encobib.model.LendingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostGreSqlLendingPeriodsRepository extends JpaRepository<LendingPeriod, Integer> {

    List<LendingPeriod> findLendingPeriodsByBookId(int id);
}
