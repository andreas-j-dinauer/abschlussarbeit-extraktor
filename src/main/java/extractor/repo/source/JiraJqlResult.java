package extractor.repo.source;

import extractor.persistance.source.JiraIssueSource;

import java.util.List;

public class JiraJqlResult {

    private List<JiraIssueSource> issues;

    public List<JiraIssueSource> getIssues() {
        return issues;
    }

    public void setIssues(List<JiraIssueSource> issues) {
        this.issues = issues;
    }
}
