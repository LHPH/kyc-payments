package com.kyc.payments.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name="KYC_USER")
public class KycUser {

    @Id
    private Long id;
    @Column(name="USERNAME")
    private String username;
    @Column(name="SECRET")
    private String secret;
    @Column(name="ACTIVE")
    private boolean active;
    @Column(name="LOCKED")
    private boolean locked;
    @Column(name="USER_TYPE")
    private Integer userType;
    @Column(name="DATE_CREATION")
    private Date dateCreation;
    @Column(name="DATE_UPDATED")
    private Date dateUpdated;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KycUser{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", active=").append(active);
        sb.append(", locked=").append(locked);
        sb.append('}');
        return sb.toString();
    }
}
