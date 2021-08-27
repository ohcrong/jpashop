package jpashop.simpleshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
    }
}
