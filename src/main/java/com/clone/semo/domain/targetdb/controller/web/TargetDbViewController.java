package com.clone.semo.domain.targetdb.controller.web;

import com.clone.semo.domain.targetdb.dto.TargetDbRegisterForm;
import com.clone.semo.domain.targetdb.service.TargetDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/target-db")
@RequiredArgsConstructor
public class TargetDbViewController {

    private final TargetDbService targetDbService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        // 빈 폼 객체를 넘겨주어 Thymeleaf에서 바인딩 가능하게 함
        model.addAttribute("registerForm", new TargetDbRegisterForm("", "", 1521, "", "", ""));
        return "targetdb/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") TargetDbRegisterForm form) {
        targetDbService.register(form);
        return "redirect:/dashboard"; // 등록 후 대시보드로 이동
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        targetDbService.deleteTargetDb(id);
        return "redirect:/dashboard"; // 삭제 후 다시 목록으로
    }
}