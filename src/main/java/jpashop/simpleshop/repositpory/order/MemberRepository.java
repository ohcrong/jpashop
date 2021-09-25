package jpashop.simpleshop.repositpory.order;

import jpashop.simpleshop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByName(String Name);

}
