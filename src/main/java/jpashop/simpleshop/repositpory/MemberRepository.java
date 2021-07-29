package jpashop.simpleshop.repositpory;

import jpashop.simpleshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //등록하면 @SpringBootApplication의 하위 페케지 component는 모두 스캔 대상
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    //spring data jpa가 @PersistenceContext -> @Autowired로 사용가능
    //final 붙이고 @RequiredArgsConstructor로 대체

    public void save(Member member) {
        em.persist(member);//transaction시점에 DB에 inset 쿼리
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
