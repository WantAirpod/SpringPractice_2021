package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    /**
     * Order와 Member의 관계설정
     * Member한명당 다수의 Order가 올수 있다. (=쇼핑중독)
     * 따라서 Member(1) : Order(다) @ManyToOne 관계를 설정한다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원


    /**
     * CascadeType.ALL 옵션이란
     * 기존 해당 orderItem 객체를 order에 넣는다고 하면,
     * persist(OrderItemA)
     * persist(OrderItemB)
     * persist(OrderItemC)
     * persist(order) 해야한다. Cascade.ALL 옵션을 사용하면,
     * persist(order) 만 해주면 된다.
     *
     */
    //@JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //@JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송 정보

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //연계관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


}
