package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author UTJ
 */

@Entity
@Table(name = "tbl_user")
@Setter
@Getter
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "last_login_date")
    private LocalDateTime last_login_date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "person_number")
    @ToString.Exclude
    private Person person;

    public User() {
        this.status = true;
        this.deleted = false;
    }
}
