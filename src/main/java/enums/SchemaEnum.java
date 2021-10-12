package enums;

public enum SchemaEnum {

    User("get-user-json-schema.json"),
    Users("get-users-json-schema.json");

    public final String value;

    SchemaEnum(String value) {
        this.value = value;
    }
}
