package extractor.core;

import java.util.List;
import java.util.Map;

import com.atlassian.jira.rest.client.api.domain.Issue;

import extractor.core.extractor.CommentExtractor;
import extractor.core.jql.JqlBuilder;
import extractor.repo.source.IssueSourceRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IssueExtractor {

    private IssueSourceRepo issueSourceRepo;
    private CommentExtractor commentExtractor;

    @Inject
    public IssueExtractor(IssueSourceRepo issueSourceRepo, CommentExtractor commentExtractor) {
        this.issueSourceRepo = issueSourceRepo;
        this.commentExtractor = commentExtractor;
    }

    public void run(Map.Entry<String, List<String>> project) {
        Iterable<Issue> issues = issueSourceRepo.findByJql(JqlBuilder.build(project.getKey(), project.getValue(), null));
        for(Issue issue : issues) {
            run(issue);
        }
    }

    public void run(Issue issue) {
        //Handle Issues here

        commentExtractor.run(issue);
    }

}