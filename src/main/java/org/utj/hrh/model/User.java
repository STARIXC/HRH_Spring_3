package org.utj.hrh.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author UTJ
 */

@Entity
@Table(name = "tbl_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @Column(name = "userid")
    private String userid;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "status")
    private int status;
    @Column(name = "created_at")
    private LocalDateTime created_at;
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", insertable = false, updatable = false)
    private Role role;


    public User(String username, String password, int roleId, int status) {
    }

    public User(String username, String password, List<GrantedAuthority> authorities) {
    }
}
