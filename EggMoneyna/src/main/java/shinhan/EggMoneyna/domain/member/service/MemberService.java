package shinhan.EggMoneyna.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.domain.member.entity.Member;
import shinhan.EggMoneyna.domain.member.repository.MemberRepository;
import shinhan.EggMoneyna.domain.member.service.dto.LoginDto;
import shinhan.EggMoneyna.global.error.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.AuthenticationException;
import shinhan.EggMoneyna.global.error.exception.BusinessException;
import shinhan.EggMoneyna.global.error.exception.EntityNotFoundException;
import shinhan.EggMoneyna.global.jwt.dto.JwtTokenDto;
import shinhan.EggMoneyna.global.jwt.service.TokenManager;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    public Member registerMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    public JwtTokenDto login (Long memberId){

        Member member = memberRepository.findById(memberId).orElseThrow();

        return tokenManager.createJwtTokenDto(
                memberId, member.getRole()
        );
    }

    private void validateDuplicateMember(Member member) throws BusinessException {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if(optionalMember.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
    }

    @Transactional(readOnly = true)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberByRefreshToken(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        LocalDateTime tokenExpirationTime = member.getTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) {
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        return member;
    }

    public Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_EXISTS));
    }
}
