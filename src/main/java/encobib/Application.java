package encobib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@Configuration
public class Application {

    //    @Bean
    //    @ConditionalOnProperty(name = "repositoryType", havingValue = "db")
    //    BooksRepositoryImpl booksRepositoryImpl(PostGreSQLBooksRepo postGreSQLBooksRepo, PostGreSqlLendingPeriodsRepository postGreSqlLendingPeriodsRepository) {
    //        return new BooksRepositoryImpl(postGreSQLBooksRepo, postGreSqlLendingPeriodsRepository);
    //    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
