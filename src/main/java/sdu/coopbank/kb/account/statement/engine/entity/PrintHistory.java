package sdu.coopbank.kb.account.statement.engine.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_print_history")
public class PrintHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Date logdate;
    private Long accountnumber;
    private String accountname;
    private Long natid;
    private int pageno;
    private String currency;
    private int charges;
    private Date startdate;
    private Date enddate;
    private String signature;
}