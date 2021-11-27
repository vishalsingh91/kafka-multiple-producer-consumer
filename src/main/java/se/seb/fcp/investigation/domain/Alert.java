package se.seb.fcp.investigation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "alerts")
public class Alert {

    @Id
    private UUID id;
    private String alertId;
    private String system;
    private String alertStatus;
    //private AdditionalData additionalData;
    private boolean caseId;
    private Date createdDate;


    public String getAlertStatus() {
        return alertStatus;
    }
    public void setAlertStatus(String alertStatus) {
        this.alertStatus = alertStatus;
    }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate;    }


    @Override
    public String toString() {
        return "Alerts{" +
                "id=" + id +
                ", alertId='" + alertId + '\'' +
                ", system='" + system + '\'' +
               // ", additionalData=" + additionalData +
                ", caseId=" + caseId +
                ", createdDate=" + createdDate +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

   /* public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }*/

    public boolean isCaseId() {
        return caseId;
    }

    public void setCaseId(boolean caseId) {
        this.caseId = caseId;
    }
}
