package com.example.infra;

import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.entity.Screening;
import com.example.domain.theater.entity.Theater;
import com.example.infra.screening.repository.ScreeningJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)// 로컬 db 사용
class ScreeningRepositoryTest {

    @Autowired
    private ScreeningJpaRepository screeningJpaRepository;

    @Autowired
    TestEntityManager testEntityManager;



    @Test
    @DisplayName("상영정보 조회")
    void testFindScreeningWithDetailsByStartTimeAfter(){
        Movie movie = Movie.builder()
                .title("Inception")
                .rating(Movie.Rating.AGE_15)
                .releaseDate(LocalDateTime.of(2010, 7, 16, 0, 0))
                .thumbnail("inception.jpg")
                .genre(Movie.Genre.ACTION)
                .runtimeMinutes(148)
                .build();
        testEntityManager.persist(movie);

        Theater theater = Theater.builder()
                .name("IMAX")
                .build();
        testEntityManager.persist(theater);

        Screening screening1 = Screening.builder()
                .movie(movie)
                .theater(theater)
                .startTime(LocalDateTime.now().plusHours(1)) // 현재 시간 이후
                .endTime(LocalDateTime.now().plusHours(3))
                .build();
        testEntityManager.persist(screening1);

        Screening screening2 = Screening.builder()
                .movie(movie)
                .theater(theater)
                .startTime(LocalDateTime.now().minusHours(1)) // 현재 시간 이전
                .endTime(LocalDateTime.now().plusHours(1))
                .build();
        testEntityManager.persist(screening2);

        testEntityManager.flush(); // 영속성 컨텍스트 강제 플러시

        LocalDateTime now = LocalDateTime.now();
        List<Screening> results = screeningJpaRepository.findScreeningWithDetailsByStartTimeAfter(now);

        assertThat(results).hasSize(1); // 조건에 맞는 데이터만 반환
        Screening result = results.get(0);
        assertThat(result.getMovie().getTitle()).isEqualTo("Inception");
        assertThat(result.getTheater().getName()).isEqualTo("IMAX");
    }

}
