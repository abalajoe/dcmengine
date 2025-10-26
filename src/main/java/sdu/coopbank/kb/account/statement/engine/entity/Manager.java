package sdu.coopbank.kb.account.statement.engine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "MANAGER")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false, length = 250)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS", nullable = false)
    private Status status;

    @CreatedBy
    @Column(name = "CREATEDBY", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "UPDATEDBY", nullable = false)
    private String updatedBy;

    @CreatedDate
    @Column(name = "DATECREATED", updatable = false)
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(name = "DATEUPDATED")
    private LocalDateTime dateUpdated;
}

