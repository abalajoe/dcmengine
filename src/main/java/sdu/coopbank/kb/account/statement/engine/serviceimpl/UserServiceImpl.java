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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sdu.coopbank.kb.account.statement.engine.dto.*;
import sdu.coopbank.kb.account.statement.engine.entity.*;
import sdu.coopbank.kb.account.statement.engine.exception.EntityExistsException;
import sdu.coopbank.kb.account.statement.engine.exception.EntityNotExistsException;
import sdu.coopbank.kb.account.statement.engine.repository.*;
import sdu.coopbank.kb.account.statement.engine.service.UserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private final ChargeWaiverRepository chargeWaiverRepository;
    private final ConfigsRepository configsRepository;
    private final DepartmentsRepository departmentsRepository;
    private final BranchesRepository branchesRepository;
    private final RolesRepository rolesRepository;
    private final ManagerRepository managerRepository;
    private final LogCategoryRepository logCategoryRepository;
    private final StatusRepository statusRepository;
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
    public Department createDepartment(DepartmentDTO departmentDTO) {
       try {
           log.info("departmentDTO >> {}", departmentDTO);
           Optional<Department> dept = departmentsRepository.findByNameIgnoreCase(departmentDTO.getName());
           log.info("departmentDTO2 >> {}", dept);
           if(dept.isPresent()) throw new EntityExistsException("The resource exists");
           log.info("departmentDTO3 >> {}", dept);

           Optional<Status> status = statusRepository.findById(2);
           if (status.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

           Department department = Department.builder()
                   .name(departmentDTO.getName())
                   .description(departmentDTO.getDescription())
                   .dateCreated(LocalDateTime.now())
                   .dateUpdated(LocalDateTime.now())
                   .createdBy("admin")
                   .updatedBy("admin")
                   .status(status.get())
                   .build();
           return departmentsRepository.save(department);
       } catch (Exception e){
           e.printStackTrace();
           log.error("error - {}", e.getMessage());
           return null;
       }
    }

    @Override
    public Branch createBranch(BranchDTO branchDTO) {
        try {
            log.info("branchDTO >> {}", branchDTO);
            Optional<Branch> branch = branchesRepository.findByNameIgnoreCase(branchDTO.getName());
            log.info("branchDTO2 >> {}", branch);
            if(branch.isPresent()) throw new EntityExistsException("The resource exists");
            log.info("branchDTO3 >> {}", branch);

            Optional<Status> status = statusRepository.findById(2);
            if (status.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

            Branch branch1 = Branch.builder()
                    .name(branchDTO.getName())
                    .description(branchDTO.getDescription())
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .createdBy("admin")
                    .updatedBy("admin")
                    .status(status.get())
                    .build();
            return branchesRepository.save(branch1);
        } catch (Exception e){
            e.printStackTrace();
            log.error("error - {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Roles createRole(RoleDTO roleDTO) {
        try {
            log.info("roleDTO >> {}", roleDTO);
            Optional<Roles> role = rolesRepository.findByNameIgnoreCase(roleDTO.getName());
            log.info("roleDTO2 >> {}", role);
            if(role.isPresent()) throw new EntityExistsException("The resource exists");
            Optional<Department> department = departmentsRepository.findById(roleDTO.getDepartment());
            if(department.isEmpty()) throw new EntityExistsException("The resource does not exists");

            Optional<Status> status = statusRepository.findById(2);
            if (status.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

            Roles role1 = Roles.builder()
                    .name(roleDTO.getName())
                    .description(roleDTO.getDescription())
                    .department(department.get())
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .createdBy("admin")
                    .updatedBy("admin")
                    .status(status.get())
                    .build();
            return rolesRepository.save(role1);
        } catch (Exception e){
            e.printStackTrace();
            log.error("error - {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Manager createManager(ManagerDTO managerDTO) {
        try {
            log.info("managerDTO >> {}", managerDTO);
            Optional<Manager> manager = managerRepository.findByNameIgnoreCase(managerDTO.getName());
            log.info("managerDTO >> {}", manager);
            if(manager.isPresent()) throw new EntityExistsException("The resource exists");
            Optional<Branch> branch = branchesRepository.findById(managerDTO.getDepartment());
            if(branch.isEmpty()) throw new EntityExistsException("The resource does not exists");

            Optional<Status> status = statusRepository.findById(2);
            if (status.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

            Manager manager1 = Manager.builder()
                    .name(managerDTO.getName())
                    .description(managerDTO.getDescription())
                    .branch(branch.get())
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .createdBy("admin")
                    .updatedBy("admin")
                    .status(status.get())
                    .build();
            return  managerRepository.save(manager1);
        } catch (Exception e){
            e.printStackTrace();
            log.error("error - {}", e.getMessage());
            return null;
        }
    }

    @Override
    public LogCategory createLogCategory(LogCategoryDTO logCategoryDTO) {
        try {
            log.info("logCategoryDTO >> {}", logCategoryDTO);
            Optional<LogCategory> logCategory = logCategoryRepository.findByNameIgnoreCase(logCategoryDTO.getName());
            log.info("logCategory >> {}", logCategory);
            if(logCategory.isPresent()) throw new EntityExistsException("The resource exists");

            Optional<Status> status = statusRepository.findById(2);
            if (status.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

            LogCategory logCategory1 = LogCategory.builder()
                    .name(logCategoryDTO.getName())
                    .description(logCategoryDTO.getDescription())
                    .dateCreated(LocalDateTime.now())
                    .dateUpdated(LocalDateTime.now())
                    .createdBy("admin")
                    .updatedBy("admin")
                    .status(status.get())
                    .build();
            return  logCategoryRepository.save(logCategory1);
        } catch (Exception e){
            e.printStackTrace();
            log.error("error - {}", e.getMessage());
            return null;
        }
    }

    @Override
    public Department updateDepartment(int id, DepartmentDTO departmentDTO) {
        Optional<Department> department = departmentsRepository.findById(id);
        if (department.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(2);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Department department1 = department.get();
        department1.setName(departmentDTO.getName());
        department1.setDescription(departmentDTO.getDescription());
        department1.setStatus(status1.get());
        department1.setDateUpdated(LocalDateTime.now());
        return departmentsRepository.save(department1);
    }

    @Override
    public Branch updateBranch(int id, BranchDTO branchDTO) {
        Optional<Branch> branch = branchesRepository.findById(id);
        if (branch.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(2);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Branch branch1 = branch.get();
        branch1.setName(branchDTO.getName());
        branch1.setDescription(branchDTO.getDescription());
        branch1.setStatus(status1.get());
        branch1.setDateUpdated(LocalDateTime.now());
        return branchesRepository.save(branch1);
    }

    @Override
    public Department updateDepartmentStatus(int id, String action) {
        Optional<Department> department = departmentsRepository.findById(id);
        if (department.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(1);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status2 = statusRepository.findById(3);
        if (status2.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Department department1 = department.get();
        if ("approve".equalsIgnoreCase(action)) {
            department1.setStatus(status1.get()); // or StatusEnum.APPROVED
        } else if ("reject".equalsIgnoreCase(action)) {
            department1.setStatus(status2.get());; // or StatusEnum.REJECTED
        } else {
            throw new IllegalArgumentException("Invalid action: must be approve or reject");
        }

        department1.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        department1.setDateUpdated(LocalDateTime.now());
        return departmentsRepository.save(department1);
    }

    @Override
    public Branch updateBranchStatus(int id, String action) {
        Optional<Branch> branch = branchesRepository.findById(id);
        if (branch.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(1);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status2 = statusRepository.findById(3);
        if (status2.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Branch branch1 = branch.get();
        if ("approve".equalsIgnoreCase(action)) {
            branch1.setStatus(status1.get()); // or StatusEnum.APPROVED
        } else if ("reject".equalsIgnoreCase(action)) {
            branch1.setStatus(status2.get());; // or StatusEnum.REJECTED
        } else {
            throw new IllegalArgumentException("Invalid action: must be approve or reject");
        }

        branch1.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        branch1.setDateUpdated(LocalDateTime.now());
        return branchesRepository.save(branch1);
    }

    @Override
    public Roles updateRole(int id, RoleDTO roleDTO) {
        log.info("updateRole {} {}", id, roleDTO);
        Optional<Roles> role = rolesRepository.findById(id);
        if (role.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(2);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Department> department = departmentsRepository.findById(roleDTO.getDepartment());
        if(department.isEmpty()) throw new EntityExistsException("The resource does not exists");

        Roles role1 = role.get();
        role1.setName(roleDTO.getName());
        role1.setDescription(roleDTO.getDescription());
        role1.setDepartment(department.get());
        role1.setStatus(status1.get());
        role1.setDateUpdated(LocalDateTime.now());
        return rolesRepository.save(role1);
    }

    @Override
    public Manager updateManager(int id, ManagerDTO managerDTO) {
        log.info("updateRole {} {}", id, managerDTO);
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(2);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Branch> branch = branchesRepository.findById(managerDTO.getDepartment());
        if(branch.isEmpty()) throw new EntityExistsException("The resource does not exists");

        Manager manager1 = manager.get();
        manager1.setName(managerDTO.getName());
        manager1.setDescription(managerDTO.getDescription());
        manager1.setBranch(branch.get());
        manager1.setStatus(status1.get());
        manager1.setDateUpdated(LocalDateTime.now());
        return managerRepository.save(manager1);
    }

    @Override
    public LogCategory updateLogCategory(int id, LogCategoryDTO logCategoryDTO) {
        log.info("updateLogCategory {} {}", id, logCategoryDTO);
        Optional<LogCategory> logCategory = logCategoryRepository.findById(id);
        if (logCategory.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(2);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        LogCategory logCategory1 = logCategory.get();
        logCategory1.setName(logCategoryDTO.getName());
        logCategory1.setDescription(logCategoryDTO.getDescription());
        logCategory1.setStatus(status1.get());
        logCategory1.setDateUpdated(LocalDateTime.now());
        return logCategoryRepository.save(logCategory1);
    }

    @Override
    public Roles updateRoleStatus(int id, String action) {
        Optional<Roles> role = rolesRepository.findById(id);
        if (role.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(1);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status2 = statusRepository.findById(3);
        if (status2.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Roles role1 = role.get();
        if ("approve".equalsIgnoreCase(action)) {
            role1.setStatus(status1.get()); // or StatusEnum.APPROVED
        } else if ("reject".equalsIgnoreCase(action)) {
            role1.setStatus(status2.get());; // or StatusEnum.REJECTED
        } else {
            throw new IllegalArgumentException("Invalid action: must be approve or reject");
        }

        role1.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        role1.setDateUpdated(LocalDateTime.now());
        return rolesRepository.save(role1);
    }

    @Override
    public Manager updateManagerStatus(int id, String action) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(1);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status2 = statusRepository.findById(3);
        if (status2.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Manager manager1 = manager.get();
        if ("approve".equalsIgnoreCase(action)) {
            manager1.setStatus(status1.get()); // or StatusEnum.APPROVED
        } else if ("reject".equalsIgnoreCase(action)) {
            manager1.setStatus(status2.get());; // or StatusEnum.REJECTED
        } else {
            throw new IllegalArgumentException("Invalid action: must be approve or reject");
        }

        manager1.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        manager1.setDateUpdated(LocalDateTime.now());
        return managerRepository.save(manager1);
    }

    @Override
    public LogCategory updateLogCategoryStatus(int id, String action) {
        Optional<LogCategory> logCategory = logCategoryRepository.findById(id);
        if (logCategory.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status1 = statusRepository.findById(1);
        if (status1.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        Optional<Status> status2 = statusRepository.findById(3);
        if (status2.isEmpty()) throw new EntityNotExistsException("The entity does not exist");

        LogCategory logCategory1 = logCategory.get();
        if ("approve".equalsIgnoreCase(action)) {
            logCategory1.setStatus(status1.get()); // or StatusEnum.APPROVED
        } else if ("reject".equalsIgnoreCase(action)) {
            logCategory1.setStatus(status2.get());; // or StatusEnum.REJECTED
        } else {
            throw new IllegalArgumentException("Invalid action: must be approve or reject");
        }

        logCategory1.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        logCategory1.setDateUpdated(LocalDateTime.now());
        return logCategoryRepository.save(logCategory1);
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
    public Page<ChargeWaiver> findAllChargeWaiver(Pageable pageable) {
        return chargeWaiverRepository.findAll(pageable);
    }

    @Override
    public Page<ChargeWaiver> findAllChargeWaiver(String search, Pageable pageable) {
        return chargeWaiverRepository.findAllByForacidContainingIgnoreCase(search, pageable);
    }

    @Override
    public Page<Configs> findAllConfigs(Pageable pageable) {
        return configsRepository.findAll(pageable);
    }

    @Override
    public Page<Configs> findAllConfigs(String search, Pageable pageable) {
        return configsRepository.findAllByParamContainingIgnoreCase(search, pageable);
    }

    @Override
    public Page<AccountManagement> findAccountManagementByEmailContainingIgnoreCase(String search, Pageable pageable) {
        log.info("hello");
//        return accountManagementRepository.findByEmailContainingIgnoreCase(search,pageable);
        return accountManagementRepository.findByEmailContainingIgnoreCaseOrBranchContainingIgnoreCaseOrManagerContainingIgnoreCase(
                search, search, search, pageable);
    }

    @Override
    public Page<Department> findAllDepartments(Pageable pageable) {
        return departmentsRepository.findAll(pageable);
    }

    @Override
    public Page<Department> findAllDepartments(String search, Pageable pageable) {
        return departmentsRepository.findAllByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentsRepository.findAll();
    }

    @Override
    public Page<Branch> findAllBranches(Pageable pageable) {
        return branchesRepository.findAll(pageable);
    }

    @Override
    public Page<Branch> findAllBranches(String search, Pageable pageable) {
        return branchesRepository.findAllByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public Page<Manager> findAllManagers(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    @Override
    public Page<Manager> findAllManagers(String search, Pageable pageable) {
        return managerRepository.findAllByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public Page<Roles> findAllRole(Pageable pageable) {
        return rolesRepository.findAll(pageable);
    }

    @Override
    public Page<Roles> findAllRole(String search, Pageable pageable) {
        return rolesRepository.findAllByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public Page<LogCategory> findAllLogCategory(Pageable pageable) {
        return logCategoryRepository.findAll(pageable);
    }

    @Override
    public Page<LogCategory> findAllLogCategory(String search, Pageable pageable) {
        return logCategoryRepository.findAllByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public List<Roles> findAllRole() {
        return rolesRepository.findAll();
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
