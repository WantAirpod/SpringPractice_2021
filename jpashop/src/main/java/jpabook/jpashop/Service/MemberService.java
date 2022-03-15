
package jpabook.jpashop.Service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // ReadOnly이면 성능이 최적화됨
public class MemberService {

    //1.필드 Injection
    @Autowired
    private MemberRepository memberRepository;

    /*
    //2.setter Injection
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //3. 생성자 Injection
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * 회원 가입
     */

    @Transactional // insert 해줘야 하므로 Transactional readonly false
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }


    /**
     * 이럼에도 동시에 들어올 확률이 있음. 멀티쓰레드 제약조건 유니크 키 사용권장
     * @param member
     */

    private void validateDuplicateMember(Member member) {
        //List<Member> findMembers = memberRepository.findByName(member.getName());
        List<Member> findMembers = memberRepository.findByName(member.getName());        //EXCEPTION
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}



