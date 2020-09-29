package task.homerent.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role implements GrantedAuthority {
    TENANT(Set.of(Permission.USER_READ, Permission.USER_WRITE)),
    LANDLORD(Set.of(Permission.USER_READ, Permission.LANDLORD_WRITE)),
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE, Permission.LANDLORD_WRITE, Permission.ADMIN_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
