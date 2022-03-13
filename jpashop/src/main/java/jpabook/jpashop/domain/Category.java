package jpabook.jpashop.domain;

import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /**
     * 실무에서는 many to many 사용 불가(하지말자)
     * many to many 는 편리한 것 같지만, 중간 테이블(category_item)에
     * 컬럼을 추가할 수 없고, 세밀하게 쿼리를 실행하기 어렵기 때문에 한계가 있다.
     * 중간 엔티티(CategoryItem)를 만들고 @ManyToOne, @OneToMany로 매핑해서 사용하자
     * 정리하면 다대다 매핑을 일대다, 다대일 매핑으로 풀어내서 사용하자.
     */
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /**
     * 연관 관계 메서드 : 이유 : 연관 관계 즉 join 관계에서는 반드시 서로 값을 조정하면서 업데이트 해줘야하는데
     * 사람이 하는 일이라서 까먹는다.
     * @param child
     *
     * 자식의 값을 넣었으면 부모에 set을 해주는 것 (자동)
     * 예시로...
     * public static void main(String[] args){
     *     Member member = new Member();
     *     Order order = new Order();
     *
     *     member.getOrder().add(order); //멤버가 주문을 했다. 주문 정보가 생성됐다.
     *     order.setMember(member); // 그렇다면 그 주문한 member를 order테이블에 넣어 줘야 한다.
     * }
     */
    //==연관 관계 메서드==//
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
