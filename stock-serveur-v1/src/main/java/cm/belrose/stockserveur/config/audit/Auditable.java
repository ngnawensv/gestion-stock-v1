package cm.belrose.stockserveur.config.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Ngnawen Samuel
 * @since 17/11/2020 18:20
 *
 * In this we use Spring Data JPA approch audit. It is read for integration with spring security
 * by the delete operation can not be audit
 * This is the Generic class which will be extends by all audited entities. She contain all the audited properties
 * @param <U>
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {

    @CreatedBy
    @Column(name = "created_by")
    protected U createdBy;
    @LastModifiedBy
    @Column(name = "update_by")
    protected U updatedBy;

    @CreatedDate
    protected LocalDateTime createdDate;
    @LastModifiedDate
    protected  LocalDateTime updateDate;

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public U getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(U updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
