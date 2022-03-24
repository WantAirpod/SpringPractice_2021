package jpabook.jpashop.domain;


import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.rmi.NotBoundException;

@Entity
@Table(name="order_item")
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //주문상품

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문

    private int orderPrice; //주문 가격
    private int count; //주문 수량

    //생성 메서드//

    /**
     * 실무에서는 엄청 복잡함.
     * @param item
     * @param orderPrice
     * @param count
     * @return
     * @throws NotBoundException
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) throws NotBoundException {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //주문한 만큼 재고에서 까줘야한다.
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel(){
        getItem().addStcck(count); /* 재고 수량을 원복해준다.*/
    }
    //==조회 로직==//

    /**
     * 주문상품 전체가 가격 조회
     * @return
     */
    public int getTotalPrice(){
        return getOrderPrice() * getCount();
    }

}
