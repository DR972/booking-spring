package by.rozmysl.booking.entity.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
/**
 * The class is used to store User objects with the <b>id</b>, <b>username</b>, <b>password</b>, <b>active</b>,
 * <b>email</b>, <b>activationCode</b>, <b>roles</b> properties
 */
@Data
@Entity
@Table(name = "пользователи")
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 5, max = 20)
    private String username;
    @Size(min = 5)
    private String password;
    @Transient
    private String passwordConfirm;
    private boolean active;
    @NotEmpty
    @Email(message = "emailError")
    private String email;
    private String activationCode;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    /**
     * The method checks that the account has not expired
     * @return  true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * The method checks that the account is not blocked
     * @return  true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * The method checks that the credentials have not expired
     * @return  true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * This method checks whether the account is activated
     * @return  property value active
     */
    @Override
    public boolean isEnabled() {
        return getActive();
    }

    /**
     * The method gets the value of the Authorities
     * @return  property value roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /**
     * The method gets the value of the password
     * @return  property value password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * The method gets the value of the active
     * @return  property value active
     */
    public boolean getActive() {
        return active;
    }
}