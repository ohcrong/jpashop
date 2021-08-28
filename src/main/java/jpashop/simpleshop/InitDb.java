package jpashop.simpleshop;

import jpashop.simpleshop.domain.Address;
import jpashop.simpleshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }
    //loading 시점에 생성

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit1(){
            Member member = new Member();
            member.setName("A");
            member.setAddress(new Address("seoul","111", "11100"));
            em.persist(member);
        }

    }
}
