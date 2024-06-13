package com.E_CommerceSync.E_CommerceSync.service.mail;

import com.E_CommerceSync.E_CommerceSync.exception.BusinessException;
import com.E_CommerceSync.E_CommerceSync.exception.reasons.BusinessExceptionReason;
import com.E_CommerceSync.E_CommerceSync.model.security.User;
import com.E_CommerceSync.E_CommerceSync.model.user.MailVerify;
import com.E_CommerceSync.E_CommerceSync.repository.security.MailVerifyRepository;
import com.E_CommerceSync.E_CommerceSync.service.security.UserService;
import com.E_CommerceSync.E_CommerceSync.utils.helper.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailVerifyRepository mailVerifyRepository;
    private final UserService userService;
    private final MailSender mailSender;

    @Override
    @Transactional
    public void reSendVerificationLink(String email) {

        isUserVerified(email);

        List<MailVerify> mailVerifyList = mailVerifyRepository.findByEmail(email);
        mailVerifyList.forEach(mailVerify -> mailVerify.setExpired(true));
        mailVerifyRepository.saveAll(mailVerifyList);

        MailVerify mailVerify = MailVerify.builder()
                .email(email)
                .build();

        mailVerifyRepository.save(mailVerify);

        User user = userService.getUserByEmail(email);

        mailSender.sendVerificationMail(
                email,
                "Resend Verification Link",
                "resend-verification",
                user.getFirstName().concat(" ").concat(user.getLastName()), mailVerify.getVerifyCode());

    }

    @Override
    @Transactional()
    public void verify(String email, String verifyCode) {


        MailVerify mailVerify = mailVerifyRepository.findByEmailAndVerifyCodeAndIsExpiredFalse(email, verifyCode)
                                    .orElseThrow(() -> new BusinessException(BusinessExceptionReason.INVALID_VERIFY_CODE));

        mailVerify.setExpired(true);
        mailVerifyRepository.save(mailVerify);

        isUserVerified(email);

        if(mailVerify.isExpiredByDate())
            throw new BusinessException(BusinessExceptionReason.VERIFY_CODE_EXPIRED);

        userService.verifyUser(email);
    }

    private void isUserVerified(String email) {
        boolean isVerified = userService.isUserVerified(email);

        if(isVerified)
            throw new BusinessException(BusinessExceptionReason.USER_ALREADY_VERIFIED);
    }
}


