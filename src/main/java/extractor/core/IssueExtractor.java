package extractor.core;

import extractor.persistance.source.JiraProjectSource;
import extractor.repo.source.IssueSourceRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IssueExtractor {

    private IssueSourceRepo issueSourceRepo;

    @Inject
    public IssueExtractor(IssueSourceRepo issueSourceRepo) {
        this.issueSourceRepo = issueSourceRepo;
    }

    public void run(JiraProjectSource project) {
    }

}