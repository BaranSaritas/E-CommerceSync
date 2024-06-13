package com.E_CommerceSync.E_CommerceSync.model.user;

import com.E_CommerceSync.E_CommerceSync.utils.mail.Constants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners({AuditingEntityListener.class})
public class MailVerify { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(Constants.MAIL_EXPIRED_MINUTES);

    @Builder.Default
    private String verifyCode = UUID.randomUUID().toString();

    private String email;

    @Builder.Default
    private boolean isExpired = false;

    public boolean isExpiredByDate() {
        return expiredAt.isBefore(LocalDateTime.now());
    }
}
