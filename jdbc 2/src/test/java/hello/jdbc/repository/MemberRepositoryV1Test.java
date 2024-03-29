package hello.jdbc.repository;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
class MemberRepositoryV1Test {

  MemberRepositoryV1 repository;

  @BeforeEach
  void beforeEach(){
    // 기본 DriverManger 사용 - 항상 새로운 커넥션 흭득
//    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);

    // 커넥션 풀링
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(URL);
    dataSource.setUsername(USERNAME);
    dataSource.setPassword(PASSWORD);
    repository = new MemberRepositoryV1(dataSource);
  }
  @Test
  void crud() throws SQLException {
    // save
    Member member = new Member("member4",10000);
    repository.save(member);

    // find by id
    Member findMember = repository.findById(member.getMemberId());
    log.info("find member =" + findMember);
    assertThat(member).isEqualTo(findMember);
    // update
    repository.update(member.getMemberId(), 20000);
    Member updateMember = repository.findById(member.getMemberId());
    assertThat(updateMember.getMoney()).isEqualTo(20000);

    // delete
    repository.delete(member.getMemberId());
    assertThatThrownBy(() -> repository.findById(member.getMemberId()))
        .isInstanceOf( NoSuchElementException.class);

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}