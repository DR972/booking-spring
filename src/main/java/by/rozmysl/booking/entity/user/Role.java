package by.rozmysl.booking.entity.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;
/**
 * The class is used to store Role objects with the <b>id</b>, <b>role</b> properties
 */
@Data
@Entity
@Table(name = "роли")
public class Role implements GrantedAuthority {
    @Id
    private long id;
    private String role;
    @Transient
    @ManyToMany(mappedBy = "роли")
    private Set<MyUser> myUsers;

    /**
     * The constructor creates a new object Role
     */
    public Role() {
    }

    /**
     * The constructor creates a new object Role with the <b>id</b>, <b>role</b> properties
     * @param id  role id
     * @param role  role
     */
    public Role(long id, String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * The method gets the value of the role property
     * @return  property value role reservation
     */
    @Override
    public String getAuthority() {
        return getRole();
    }
}
