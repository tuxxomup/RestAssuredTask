package models;

public class User {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    User() {
    }

    public User(Integer id, String name, String username, String email, String website,
        String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.website = website;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    //Hardcoded user object for test purpose and assertions
    public static User prepareDelphineUser() {
        User user =
            new User(9, "Glenna Reichert", "Delphine", "Chaim_McDermott@dana.io", "conrad.com",
                "(775)976-6794 x41206");
        Address address = new Address("Dayna Park", "Suite 449", "Bartholomebury", "76495-3109");
        Geo geo = new Geo(24.6463, -168.8889);
        address.setGeo(geo);
        user.setAddress(address);
        Company company = new Company("Yost and Sons", "Switchable contextually-based project",
            "aggregate real-time technologies");
        user.setCompany(company);

        return user;
    }
}
