package com.example.issuetrackingsystem.domain.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    public List<IssueEntity> findAll() {
        return issueRepository.findAll();
    }

    @Transactional  // 登録処理のためトランザクションを設定
    public void create(String summary, String description) {
        issueRepository.insert(summary, description);
    }

    public IssueEntity findById(long issueId) {
        return issueRepository.findById(issueId);
    }

    public void delete(long issueId) {
        issueRepository.delete(issueId);
    }
}
