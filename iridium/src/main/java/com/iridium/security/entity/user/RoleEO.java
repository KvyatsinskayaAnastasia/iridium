package com.iridium.security.entity.user;

import com.iridium.common.entity.AbstractEntityEO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "app_role")
@Getter
@Setter
public class RoleEO extends AbstractEntityEO implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleNameEO name;

    /**
     * Get role name.
     * @return authority
     */
    @Override
    public final String getAuthority() {
        return getName().getValue();
    }
}
