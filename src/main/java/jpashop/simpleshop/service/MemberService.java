package jpashop.simpleshop.service;

import jpashop.simpleshop.domain.Member;
import jpashop.simpleshop.repositpory.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //public 메서드에는 공통 적용
public class MemberService {

    private final MemberRepository memberRepository;
    //변경 방지, 컴파일시점에 생성자 다시 체크

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //setter인젝션처럼 변경 방지
    //test에도 직접 주입하므로 놓치지 않음
    //lombok - @RequiredArgsConstructor 사용시 final필드의 컨스트럭터 autowired 자동 생성

    /**
     * 회원가입
     */
    @Transactional //쓰기일때는 readOnly = false
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        //EXCEPTION
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
    }
}
