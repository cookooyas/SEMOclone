package com.clone.semo.common.controller;

import com.clone.semo.common.util.JasyptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class SemoCommonRestController {
    private final JasyptUtil jasyptUtil;

    @GetMapping("/encrypt")
    public String encrypt(@RequestParam String text){
        return jasyptUtil.encrypt(text);
    }

    @GetMapping("/decrypt")
    public String decrypt(@RequestParam String text){
        return jasyptUtil.decrypt(text);
    }
}