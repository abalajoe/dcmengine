package sdu.coopbank.kb.account.statement.engine.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sdu.coopbank.kb.account.statement.engine.dto.DepartmentDTO;
import sdu.coopbank.kb.account.statement.engine.dto.RoleDTO;
import sdu.coopbank.kb.account.statement.engine.dto.UserCreateRequest;
import sdu.coopbank.kb.account.statement.engine.entity.*;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(UserCreateRequest userCreateRequest);
    Department createDepartment(DepartmentDTO departmentDTO);
    Roles createRole(RoleDTO roleDTO);
    Department updateDepartment(int id, DepartmentDTO departmentDTO);
    Roles updateRole(int id, RoleDTO roleDTO);
    Department updateDepartmentStatus(int id, String action);
    Roles updateRoleStatus(int id, String action);
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

    Page<Roles> findAllRole(Pageable pageable);
    Page<Roles> findAllRole(String search, Pageable pageable);
    List<Roles> findAllRole();
}
