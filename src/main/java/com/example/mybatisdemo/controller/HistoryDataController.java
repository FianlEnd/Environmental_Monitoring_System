package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.*;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class HistoryDataController {
    @GetMapping("/getData")
    public Result GetHistoryDataController() {
        //GetHistoryData.sendEmail();
        return Result.success();
    }
}
