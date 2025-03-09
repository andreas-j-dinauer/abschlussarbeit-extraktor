package extractor.repo.source;

import java.util.List;

import extractor.persistance.source.IssueSource;

public class JqlResult {

    private List<IssueSource> issues;

    public List<IssueSource> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueSource> issues) {
        this.issues = issues;
    }
}
