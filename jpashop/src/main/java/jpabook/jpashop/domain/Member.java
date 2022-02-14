package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    /**
     * 엔티티의 식별자는 id를 사용하고 PK 컬렴명은 member_id를 사용했다.
     * 엔티티는 타입(여기서는 Member)이 있으므로 id 필드만으로 쉽게 구분가능
     * 테이블은 타입이 없으므로 구분이 어렵다. 그리고 테이블은 관례상 테이블명+Id를 많이 사용한다.
     * 참고로 객체에서 id 대신에 memberId를 사용해도 된다.
     * 중요한 것은 일관성이다.
     */
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    //private Long member_id;

    private String name;

    @Embedded
    private Address address;

    //@JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
