package sdu.coopbank.kb.account.statement.engine.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "CHARGEWAIVER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeWaiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "STMT_NO", nullable = false)
    private Long stmtNo;

    @Column(name = "FORACID", length = 20)
    private String foracid;

    @Column(name = "STARTDATE", length = 50)
    private String startDate;

    @Column(name = "ENDDATE", length = 50)
    private String endDate;

    @Column(name = "NUMPAGES", length = 10)
    private String numPages;

    @Column(name = "CHARGES", length = 10)
    private String charges;

    @Column(name = "CURUSER", length = 100)
    private String curUser;

    @Column(name = "LINEMGR", length = 100)
    private String lineMgr;

    @Column(name = "STATUS", length = 10)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STMT_DATE", nullable = false, insertable = false, updatable = false)
    private Date stmtDate;
}

