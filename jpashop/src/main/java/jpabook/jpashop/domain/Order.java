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

    //==생성 메서드==/

    /**
     * 상품 주문 기능
     * @param member 멤버정보
     * @param delivery 배송정보
     * @param orderItems 아이템들(리스트로)
     * @return
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems ){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER); /* 상태 */
        order.setOrderDate(LocalDateTime.now()); /* 주문 시간 정보 */
        return order;
    }

    /**
     * 주문취소 (비즈니스 로직)
     */
    public void cancel(){
        //이미 배송 되었으면 주문취소불가
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        //취소 상태로 변경
        this.setStatus(OrderStatus.CANCEL);
        //재고 상태를 원복한다.
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();  //오더아이템에 재고를 넣는 메서드 생성
        }
    }

    /**
     * 전체 주문 가격 조회 (비즈니스 로직)
     * 자바 람다 스트림을 이용하면 더 깔금하게 처리 가능
     * @return
     */
    public int getTotalPrice(){
        return orderItems
                .stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }


}
