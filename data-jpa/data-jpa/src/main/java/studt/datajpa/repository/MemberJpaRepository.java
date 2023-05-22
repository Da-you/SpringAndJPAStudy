package studt.datajpa.repository;

import org.springframework.stereotype.Repository;
import studt.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository // Spring Data JPA가 인식해서 구현체를 만들어서 스프링 빈에 등록해준다.
public class MemberJpaRepository {
    @PersistenceContext // JPA의 EntityManager를 주입받을 수 있다.
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // JPQL
    }
    public void delete(Member member) {
        em.remove(member);
    }
}