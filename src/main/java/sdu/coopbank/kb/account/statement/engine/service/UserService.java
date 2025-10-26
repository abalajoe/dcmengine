package sdu.coopbank.kb.account.statement.engine.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sdu.coopbank.kb.account.statement.engine.dto.*;
import sdu.coopbank.kb.account.statement.engine.entity.*;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(UserCreateRequest userCreateRequest);
    Department createDepartment(DepartmentDTO departmentDTO);
    Branch createBranch(BranchDTO branchDTO);
    Roles createRole(RoleDTO roleDTO);

    Manager createManager(ManagerDTO managerDTO);
    LogCategory createLogCategory(LogCategoryDTO logCategoryDTO);
    Department updateDepartment(int id, DepartmentDTO departmentDTO);
    Branch updateBranch(int id, BranchDTO branchDTO);
    Roles updateRole(int id, RoleDTO roleDTO);
    Manager updateManager(int id, ManagerDTO managerDTO);
    LogCategory updateLogCategory(int id, LogCategoryDTO logCategoryDTO);
    Department updateDepartmentStatus(int id, String action);
    Branch updateBranchStatus(int id, String action);
    Roles updateRoleStatus(int id, String action);
    Manager updateManagerStatus(int id, String action);
    LogCategory updateLogCategoryStatus(int id, String action);
    User edit(UserCreateRequest userCreateRequest);

    Page<User> findByNameContainingIgnoreCase(String search, Pageable pageable);
    Page<User> findAllPageable(Pageable pageable);
    Page<PrintHistory> findAllPrintHistory(Pageable pageable);
    Page<PrintHistory> findPrintHistoryByEmailContainingIgnoreCase(String search, Pageable pageable);

    Page<AccountManagement> findAllAccountManagement(Pageable pageable);
    Page<AccountManagement> findAccountManagementByEmailContainingIgnoreCase(String search, Pageable pageable);
    Page<ChargeWaiver> findAllChargeWaiver(Pageable pageable);
    Page<ChargeWaiver> findAllChargeWaiver(String search, Pageable pageable);

    Page<Configs> findAllConfigs(Pageable pageable);
    Page<Configs> findAllConfigs(String search, Pageable pageable);
    Page<Department> findAllDepartments(Pageable pageable);
    Page<Department> findAllDepartments(String search, Pageable pageable);
    List<Department> findAllDepartments();
    List<Branch> findAllBranches();

    Page<Branch> findAllBranches(Pageable pageable);
    Page<Branch> findAllBranches(String search, Pageable pageable);

    Page<Manager> findAllManagers(Pageable pageable);
    Page<Manager> findAllManagers(String search, Pageable pageable);
    Page<Roles> findAllRole(Pageable pageable);
    Page<Roles> findAllRole(String search, Pageable pageable);

    Page<LogCategory> findAllLogCategory(Pageable pageable);
    Page<LogCategory> findAllLogCategory(String search, Pageable pageable);
    List<Roles> findAllRole();
}
