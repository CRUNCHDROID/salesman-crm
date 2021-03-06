package br.com.kproj.salesman.infrastructure.entity.leads;

import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name="leads")
public class LeadEntity extends Identifiable {

	private static final long serialVersionUID = -7486201820229036695L;

    @Id
    @GeneratedValue
    private Long id;

	@NotNull @Size(min = 2, max = 100)
    private String name;

    @Email
    private String email;

    private String position;

    private String company;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="createdby_id")
    @NotNull(message = "lead.createdby.is.null")
    private UserEntity createdBy;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lead")
    protected List<LeadAddress> addresses;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lead")
    protected List<LeadPhone> phones;


    public LeadEntity() {}

    public LeadEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<LeadAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<LeadAddress> addresses) {
        this.addresses = addresses;
    }

    public List<LeadPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<LeadPhone> phones) {
        this.phones = phones;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }
}
