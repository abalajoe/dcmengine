package sdu.coopbank.kb.account.statement.engine.serviceimpl;

import com.google.gson.JsonObject;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdu.coopbank.kb.account.statement.engine.dto.AccountStatementModule;
import sdu.coopbank.kb.account.statement.engine.dto.StatementModel;
import sdu.coopbank.kb.account.statement.engine.dto.UserCreateRequest;
import sdu.coopbank.kb.account.statement.engine.entity.AccountManagement;
import sdu.coopbank.kb.account.statement.engine.entity.PrintHistory;
import sdu.coopbank.kb.account.statement.engine.entity.Role;
import sdu.coopbank.kb.account.statement.engine.entity.User;
import sdu.coopbank.kb.account.statement.engine.exception.EntityNotExistsException;
import sdu.coopbank.kb.account.statement.engine.repository.AccountManagementRepository;
import sdu.coopbank.kb.account.statement.engine.repository.PrintHistoryRepository;
import sdu.coopbank.kb.account.statement.engine.repository.RoleRepository;
import sdu.coopbank.kb.account.statement.engine.repository.UserRepository;
import sdu.coopbank.kb.account.statement.engine.service.UserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrintHistoryRepository printHistoryRepository;
    private final AccountManagementRepository accountManagementRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    ResourceLoader resourceLoader;
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateRequest userCreateRequest) {

        Optional<Role> role = roleRepository.findByName(userCreateRequest.getRole());
        if (role.isEmpty()) throw new EntityNotExistsException("The entity is not found");
        User user = User.builder()
                .name(userCreateRequest.getEmail())
                .email(userCreateRequest.getEmail())
                .datecreated(new Date())
                .roles(role.get())
                .password(passwordEncoder.encode("joe@123"))
                .build();
        return userRepository.save(user);
    }

    @Override
    public User edit(UserCreateRequest userCreateRequest) {
        Optional<User> user = userRepository.findById(userCreateRequest.getId());
        if(user.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        User user1 = user.get();
        user1.setName(userCreateRequest.getEmail());
        return userRepository.save(user1);
    }

    @Override
    public Page<User> findByNameContainingIgnoreCase(String search, Pageable pageable) {
        log.info("hello");
        return userRepository.findByNameContainingIgnoreCase(search,pageable);
    }

    @Override
    public Page<User> findAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<PrintHistory> findAllPrintHistory(Pageable pageable) {
        return printHistoryRepository.findAll(pageable);
    }

    @Override
    public Page<PrintHistory> findPrintHistoryByEmailContainingIgnoreCase(String search, Pageable pageable) {
        log.info("hello");
        return printHistoryRepository.findByEmailContainingIgnoreCase(search,pageable);
    }

    @Override
    public Page<AccountManagement> findAllAccountManagement(Pageable pageable) {
        return accountManagementRepository.findAll(pageable);
    }

    @Override
    public Page<AccountManagement> findAccountManagementByEmailContainingIgnoreCase(String search, Pageable pageable) {
        log.info("hello");
//        return accountManagementRepository.findByEmailContainingIgnoreCase(search,pageable);
        return accountManagementRepository.findByEmailContainingIgnoreCaseOrBranchContainingIgnoreCaseOrManagerContainingIgnoreCase(
                search, search, search, pageable);
    }

    public String generateAccountStatement(StatementModel stmObj, Map<String, String> env) {
//    public List<AccountStatementModule> generateAccountPDFStatement(@RequestBody StatementModel stmObj) {
        String curTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String AccId = stmObj.getAccountNo();
        String fname = "Account_Statement_" + curTime + ".pdf";
        JsonObject jsonResp = new JsonObject();

        String startDate = formartedDate2(stmObj.getStartDt());
        String endDate = formartedDate2(stmObj.getEndDt());

        //Query statement and return list
//        List<AccountStatementModule> AccList = accList.custAccStament(stmObj.getAccountNo(), startDate, endDate,env);
        List<AccountStatementModule> AccList = list();
        log.info("AccList - {} ",AccList);

//        String pstAdrs = accList.getCustomerPostAddress(stmObj.getAccountNo());
        String pstAdrs = "Postal Address";

        log.info("Postal Address" + pstAdrs);
//        String accountDescription=accList.getCustomerAccountDescription(stmObj.getAccountNo());
        String accountDescription="Account Desc";
        log.info("Account description" + accountDescription);


        try {

//            String reportPath = env.get("pdfPath"); rootFolder
            Path pdfsPath = Paths.get("pdfStatements");
            String reportPath = new StringBuilder().append(pdfsPath.toAbsolutePath()).append("/").append(fname).toString();

            InputStream reportInputStream = resourceLoader.getResource("classpath:Reports/AccountStatement.jasper").getInputStream();

            JasperReport jr = (JasperReport) JRLoader.loadObject(reportInputStream);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(AccList);
            Map parameters = new HashMap();

            AccList.stream().map(acst -> {
                parameters.put("accountName", acst.getACCOUNT_NAME());
                return acst;
            }).map(acst -> {
                parameters.put("accNo", acst.getFORACID());
                return acst;
            }).map(acst -> {
                parameters.put("branchCode", acst.getSOL_ID());
                return acst;
            }).map(acst -> {
                parameters.put("currency", acst.getTRAN_CRNCY_CODE());
                return acst;
            }).forEachOrdered(_item -> {
                parameters.put("stmPeriod", startDate + " TO " + endDate);
            });
            parameters.put("postAddress",pstAdrs);
            parameters.put("ACCOUNT_DESCRIPTION",accountDescription);
            File file = null;

            InputStream image = resourceLoader.getResource("classpath:Current-logo.png").getInputStream();
//            Image img = Image.getInstance(image.readAllBytes());
            parameters.put("logo", image);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, parameters, beanColDataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath);

            file = new File(reportPath);
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String pdfStm = Base64.getEncoder().encodeToString(fileContent);

            jsonResp.addProperty("status", "00");
            jsonResp.addProperty("base64", pdfStm);

//            After viewing this file, delete it
            file.delete();
        } catch (IOException | JRException ex) {
            log.error(ex.getMessage());
            jsonResp.addProperty("status", "FAIL");
            jsonResp.addProperty("status", "Statement Sending was not successful!");
        }
        return String.valueOf(jsonResp);
    }

    public String formartedDate2(Date dt) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateformated = null;
        try {
            dateformated = formatter.format(dt);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return dateformated;
    }

    public List<AccountStatementModule> list(){
        List<AccountStatementModule> list = new ArrayList<>();

        AccountStatementModule account1 = new AccountStatementModule();
        account1.setId(1);
        account1.setACCOUNT_NAME("John Doe");
        account1.setORGKEY("ORG001");
        account1.setCUST_SHORT_NAME("JD");
        account1.setFORACID("1234567890");
        account1.setREF_NUM("REF1001");
        account1.setTRAN_RMKS("Salary Credit");
        account1.setSYS_PART_TRAN_CODE("CR");
        account1.setTRAN_CRNCY_CODE("KES");
        account1.setSOL_ID("SOL001");
        account1.setAMOUNTDEBIT(0.0);
        account1.setAMOUNTCREDIT(50000.00);
        account1.setTRAN_AMT("50000.00");
        account1.setVALUE_DATE("2025-10-01");
        account1.setTRAN_DATE("2025-10-01");
        account1.setPSTD_DATE("2025-10-02");
        account1.setAVAILABLE_AMT("100000.00");
        account1.setACCT_BALANCE("150000.00");
        account1.setSVS_TRAN_ID("TRN001");
        account1.setREF_AMT("50000.00");
        account1.setPSTD_USER_ID("admin");
        account1.setTRAN_TYPE("CREDIT");
        account1.setPART_TRAN_SRL_NUM("001");
        account1.setREVERSAL_STATUS("N");
        account1.setVOUCHER_PRINT_FLG("Y");
        account1.setSERIAL_NUM("SR001");
        account1.setTRAN_ID("T001");
        account1.setENTRY_USER_ID("admin");
        account1.setPART_TRAN_TYPE("MAIN");
        account1.setVFD_USER_ID("VFD001");
        account1.setRATE("1.0");
        account1.setORIGINALAMOUNT("50000.00");
        account1.setCHANNEL("ONLINE");
        account1.setDS_PHYSICAL_ADDRESS("Nairobi, Kenya");
        account1.setSTATE("Nairobi");
        account1.setPOSTAL_CODE("00100");
        account1.setPOSTAL_ADDRESS("P.O. Box 1234");
        account1.setACCOUNT_DESCRIPTION("Savings Account");
        account1.setUNCLEARED_BAL(0.0);

        // Second object
        AccountStatementModule account2 = new AccountStatementModule();
        account2.setId(2);
        account2.setACCOUNT_NAME("Mary Wanjiku");
        account2.setORGKEY("ORG002");
        account2.setCUST_SHORT_NAME("MW");
        account2.setFORACID("0987654321");
        account2.setREF_NUM("REF2001");
        account2.setTRAN_RMKS("Utility Payment");
        account2.setSYS_PART_TRAN_CODE("DR");
        account2.setTRAN_CRNCY_CODE("KES");
        account2.setSOL_ID("SOL002");
        account2.setAMOUNTDEBIT(1500.00);
        account2.setAMOUNTCREDIT(0.0);
        account2.setTRAN_AMT("1500.00");
        account2.setVALUE_DATE("2025-10-03");
        account2.setTRAN_DATE("2025-10-03");
        account2.setPSTD_DATE("2025-10-03");
        account2.setAVAILABLE_AMT("48500.00");
        account2.setACCT_BALANCE("48500.00");
        account2.setSVS_TRAN_ID("TRN002");
        account2.setREF_AMT("1500.00");
        account2.setPSTD_USER_ID("cashier1");
        account2.setTRAN_TYPE("DEBIT");
        account2.setPART_TRAN_SRL_NUM("002");
        account2.setREVERSAL_STATUS("N");
        account2.setVOUCHER_PRINT_FLG("N");
        account2.setSERIAL_NUM("SR002");
        account2.setTRAN_ID("T002");
        account2.setENTRY_USER_ID("cashier1");
        account2.setPART_TRAN_TYPE("CHARGES");
        account2.setVFD_USER_ID("VFD002");
        account2.setRATE("1.0");
        account2.setORIGINALAMOUNT("1500.00");
        account2.setCHANNEL("BRANCH");
        account2.setDS_PHYSICAL_ADDRESS("Mombasa, Kenya");
        account2.setSTATE("Mombasa");
        account2.setPOSTAL_CODE("80100");
        account2.setPOSTAL_ADDRESS("P.O. Box 5678");
        account2.setACCOUNT_DESCRIPTION("Current Account");
        account2.setUNCLEARED_BAL(200.00);

        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        list.add(account1);
        list.add(account2);
        return list;
    }
}
