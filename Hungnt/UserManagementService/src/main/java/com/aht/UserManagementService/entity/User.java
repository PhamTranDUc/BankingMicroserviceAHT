package com.aht.UserManagementService.entity;

import com.aht.UserManagementService.Validation.User.EmailNotExists;
import com.aht.UserManagementService.Validation.User.UsernameNotExists;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private static final long serialVersionUID = 1L;

    @Column(name = "userId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NonNull
    @NotBlank(message = "{User.createUser.username.NotBlank}")
    @Length(min = 6, max = 30, message = "{User.createUser.username.LenghtRange}")
    @UsernameNotExists(message = "{User.createUser.username.NotExists}")
    private String username;

    @Column(name = "password", length = 500, nullable = false)
    @NotBlank(message = "{User.createUser.password.NotBlank}")
    @Length(min = 6, max = 30, message = "{User.createUser.password.LenghtRange}")
    private String password;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    @NotBlank(message = "{User.createUser.email.NotBlank}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email not valid")
    @EmailNotExists(message = "{User.createUser.email.NotExists}")
    private String email;

    @Column(name = "fullname", length = 50, nullable = false)
    @NotBlank(message = "{User.createUser.fullname.NotBlank}")
    @Length(min = 10, max = 50, message = "{User.createUser.fullname.LenghtRange}")
    private String fullname;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date created_at;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
    private Set<Role> roles;
}
