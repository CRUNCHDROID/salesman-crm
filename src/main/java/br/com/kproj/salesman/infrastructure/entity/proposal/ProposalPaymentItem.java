package br.com.kproj.salesman.infrastructure.entity.proposal;


import br.com.kproj.salesman.auditing.infrastructure.ExcludeAuditingField;
import br.com.kproj.salesman.infrastructure.entity.Identifiable;
import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
@Entity
@Table(name="proposal_payment_item")
public class ProposalPaymentItem extends Identifiable {

    @Id
    @GeneratedValue
    @Expose
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/M/Y")
    @NotNull(message = "proposal.payment.duedate.is.invalid")
    @Column(name="due_date")
    @Expose
    private Date dueDate;

    @NotNull(message = "proposal.payment.value.is.invalid")
    @Expose
    private BigDecimal value;

    @Expose
    private String observation;

    @ManyToOne
    @JoinColumn(name="business_proposal_id")
    @ExcludeAuditingField
    private BusinessProposal businessProposal;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public BusinessProposal getBusinessProposal() {
        return businessProposal;
    }

    public void setBusinessProposal(BusinessProposal businessProposal) {
        this.businessProposal = businessProposal;
    }
}
