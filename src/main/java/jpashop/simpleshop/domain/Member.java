package jpashop.simpleshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    //컬렉션은 필드에서 바로 초기화가 best practice
    //하이버네이트는 엔티티 영속화 할때, 컬랙션을 하이버네이트의 내장 컬랙션으로 변경하기 때문에, 필드레벨에서 생성
}
