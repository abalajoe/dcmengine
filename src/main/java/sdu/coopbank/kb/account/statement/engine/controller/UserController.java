package sdu.coopbank.kb.account.statement.engine.controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.coopbank.kb.account.statement.engine.dto.*;
import sdu.coopbank.kb.account.statement.engine.entity.*;
import sdu.coopbank.kb.account.statement.engine.service.UserService;
import sdu.coopbank.kb.account.statement.engine.serviceimpl.UserServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
//@RequestMapping("/api/accountstatementengine/v1/user")
@RequestMapping("/api/accountstatementengine/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    public HashMap<String, String> params;
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/findAll")
    public Page<User> findAll(@RequestParam("start") int start,
                              @RequestParam("length") int length,
                              @RequestParam(value = "searchVal", required = false) String searchVal,
                              @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("start {} length {}", start, length);
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        if (searchVal != null && !searchVal.isEmpty()) {
            return userService.findByNameContainingIgnoreCase(searchVal, pageable);
        } else {
            return userService.findAllPageable(pageable);
        }
    }

    @GetMapping("/findAllPrintHistory")
    public Page<PrintHistory> findAllPrintHistory(@RequestParam("start") int start,
                                            @RequestParam("length") int length,
                                            @RequestParam(value = "searchVal", required = false) String searchVal,
                                            @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("start {} length {}", start, length);
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        if (searchVal != null && !searchVal.isEmpty()) {
            return userService.findPrintHistoryByEmailContainingIgnoreCase(searchVal, pageable);
        } else {
            return userService.findAllPrintHistory(pageable);
        }
    }

    @GetMapping("/findAllAccountManagement")
    public Page<AccountManagement> findAllAccountManagement(@RequestParam("start") int start,
                                                            @RequestParam("length") int length,
                                                            @RequestParam(value = "searchVal", required = false) String searchVal,
                                                            @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllAccountManagement => start={} length={} sort={} searchVal={}",
                start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return userService.findAccountManagementByEmailContainingIgnoreCase(
                    searchVal.trim(), pageable);
        } else {
            return userService.findAllAccountManagement(pageable);
        }
    }

    @GetMapping("/findAllChargeWaiver")
    public Page<ChargeWaiver> findAllChargeWaiver(@RequestParam("start") int start,
                                                  @RequestParam("length") int length,
                                                  @RequestParam(value = "searchVal", required = false) String searchVal,
                                                  @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllChargeWaiver => start={} length={} sort={} searchVal={}",
                start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return userService.findAllChargeWaiver(
                    searchVal.trim(), pageable);
        } else {
            return userService.findAllChargeWaiver(pageable);
        }
    }

    @GetMapping("/findAllConfigs")
    public Page<Configs> findAllConfigs(@RequestParam("start") int start,
                                        @RequestParam("length") int length,
                                        @RequestParam(value = "searchVal", required = false) String searchVal,
                                        @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllConfigs => start={} length={} sort={} searchVal={}",
                start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return userService.findAllConfigs(
                    searchVal.trim(), pageable);
        } else {
            return userService.findAllConfigs(pageable);
        }
    }

    @GetMapping("/findAllDepartments")
    public Page<Department> findAllDepartments(@RequestParam("start") int start,
                                        @RequestParam("length") int length,
                                        @RequestParam(value = "searchVal", required = false) String searchVal,
                                        @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllConfigs => start={} length={} sort={} searchVal={}",
                start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return userService.findAllDepartments(
                    searchVal.trim(), pageable);
        } else {
            return userService.findAllDepartments(pageable);
        }
    }

    @GetMapping("/findAllRole")
    public Page<Roles> findAllRoles(@RequestParam("start") int start,
                                               @RequestParam("length") int length,
                                               @RequestParam(value = "searchVal", required = false) String searchVal,
                                               @RequestParam(defaultValue = "id,desc") String[] sort) {
        log.info("findAllRoles => start={} length={} sort={} searchVal={}",
                start, length, Arrays.toString(sort), searchVal);

        // ✅ Defensive check — avoid IndexOutOfBounds if client sends malformed sort param
        String sortField = sort.length > 0 ? sort[0] : "id";
        String sortDir = sort.length > 1 ? sort[1] : "desc";

        Sort.Direction direction = Sort.Direction.fromString(sortDir.toUpperCase());
//        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sort[0]));
        Pageable pageable = PageRequest.of(start, length, Sort.by(direction, sortField));
        if (searchVal != null && !searchVal.trim().isEmpty()) {
            return userService.findAllRole(
                    searchVal.trim(), pageable);
        } else {
            return userService.findAllRole(pageable);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.create(userCreateRequest);
        log.info("user {}", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findAllDepartment")
    public ResponseEntity<List<Department>> findAllDepartments() {
        List<Department> department = userService.findAllDepartments();
        log.info("department {}", department);
        return ResponseEntity.ok(department);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> create(@RequestBody DepartmentDTO departmentDTO) {
        log.info("departmentDTO {}", departmentDTO);

        Department department = userService.createDepartment(departmentDTO);
        log.info("department {}", department);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable int id,
            @RequestBody DepartmentDTO departmentDTO) {

        Department updated = userService.updateDepartment(id, departmentDTO);
        log.info("department {}", updated);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/department/{id}/status")
    public ResponseEntity<Department> updateDepartmentStatus(
            @PathVariable int id,
            @RequestParam("action") String action) {

        Department updated = userService.updateDepartmentStatus(id, action);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Roles>> findAllRoles() {
        List<Roles> roles = userService.findAllRole();
        log.info("department {}", roles);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/role")
    public ResponseEntity<Roles> create(@RequestBody RoleDTO roleDTO) {
        log.info("roleDTO {}", roleDTO);

        Roles role = userService.createRole(roleDTO);
        log.info("department {}", role);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<Roles> updateDepartment(
            @PathVariable int id,
            @RequestBody RoleDTO roleDTO) {

        Roles updated = userService.updateRole(id, roleDTO);
        log.info("department {}", updated);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/role/{id}/status")
    public ResponseEntity<Roles> updateRoleStatus(
            @PathVariable int id,
            @RequestParam("action") String action) {

        Roles updated = userService.updateRoleStatus(id, action);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/edit")
    public ResponseEntity<User> edit(@RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.edit(userCreateRequest);
        log.info("user {}", user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/accountStatement", consumes = "application/json; X-Content-Type-Options=nosnif", produces = "application/json")
//    public ResponseEntity<AccountStatementModule> usersAccountStmList(@RequestBody StatementModel stm) {
    public ResponseEntity<AccountStatementModule> usersAccountStmList() {

        StatementModel stmdl = new StatementModel();
//        stmdl.setAccountNo(stm.getAccountNo());
//        stmdl.setStartDt(stm.getStartDt());
//        stmdl.setEndDt(stm.getEndDt());
        log.info("hello");
        stmdl.setAccountNo("123456");
        stmdl.setStartDt(new Date());
        stmdl.setEndDt(new Date());

        params = new HashMap<>();
        // Date format yyyy-mm-dd
        String pdfGen = null;
        JsonObject resp = new JsonObject();
        try {
            pdfGen = userServiceImpl.generateAccountStatement(stmdl, params);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            resp.addProperty("status", "FAIL");
            resp.addProperty("status", "Statement generation was not successful!");
            pdfGen = String.valueOf(resp);
        }
        return new ResponseEntity(pdfGen, HttpStatus.OK);
    }
}

