package com.example.issuetrackingsystem.web.issue;

import com.example.issuetrackingsystem.domain.issue.IssueEntity;
import com.example.issuetrackingsystem.domain.issue.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    // GET /issues
    @GetMapping
    public String showList(Model model){
        model.addAttribute("issueList", issueService.findAll());
        return "issues/list";
    }

    // GET /issues/creationForm
    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute IssueForm form) {
        return "issues/creationForm";
    }

    // POST /issues
    @PostMapping
    public String create(@Validated IssueForm form, BindingResult bindingResult, Model model) {
        // バリデーションエラーが発生した場合、再度フォーム画面に戻す
        if (bindingResult.hasErrors()) {
            return showCreationForm(form);
        }

        issueService.create(form.getSummary(), form.getDescription());
        // POSTの二重送信を対策し、/issuesページに送信
        return "redirect:/issues";
    }

    // GET localhost:8080/issues/1
    @GetMapping("/{issueId}")
    public String showDetail(@PathVariable("issueId") long issueId, Model model) {
        var dummyEntity = new IssueEntity(1, "概要", "説明");
        model.addAttribute("issue", issueService.findById(issueId));
        return "issues/detail";
    }

    // POST localhost:8080/issues/1 完了ボタンのクリックで課題が削除される
    @PostMapping("/{issueId}")
    public String deleteIssue(@PathVariable("issueId") long issueId){
        issueService.delete(issueId);
        return "redirect:/issues";
    }
}
