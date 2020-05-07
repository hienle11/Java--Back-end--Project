package entity;

import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "provider")
public class Provider extends AbstractEntity<Long>{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "provider name must not be blank")
    private String name;

    @Column
    @NotBlank(message = "provider address must not be blank")
    private String address;

    @Column
    @NotBlank
    private String phone;

    @Column
    @NotBlank
    private String fax;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank(message = "provider contact person must not be blank")
    private String contactPerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}
