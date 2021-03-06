package task.homerent.model;

public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ADMIN_WRITE("admin:write"),
    LANDLORD_WRITE("landlord:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
