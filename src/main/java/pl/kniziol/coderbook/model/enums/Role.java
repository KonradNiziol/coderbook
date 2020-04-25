package pl.kniziol.coderbook.model.enums;

public enum Role {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
