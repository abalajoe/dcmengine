package sdu.coopbank.kb.account.statement.engine.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.coopbank.kb.account.statement.engine.dto.UserCreateRequest;
import sdu.coopbank.kb.account.statement.engine.entity.User;
import sdu.coopbank.kb.account.statement.engine.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/accountstatementengine/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

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

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.create(userCreateRequest);
        log.info("user {}", user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/edit")
    public ResponseEntity<User> edit(@RequestBody UserCreateRequest userCreateRequest) {
        User user = userService.edit(userCreateRequest);
        log.info("user {}", user);
        return ResponseEntity.ok(user);
    }
}

