package extractor.repo.source;

import extractor.core.JqlBuilder;
import extractor.persistance.source.JiraIssueSource;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class IssueSourceRepo {

    public IssueSourceRepo() {
    }

    public List<JiraIssueSource> findByJql(String jql) {
        System.out.println(jql);
        return JiraSourceClient.build().findByJql(jql).getIssues();
    }

    public List<JiraIssueSource> findByProjectKeyAndCreatedSince(String projectKey, ZonedDateTime createdSince) throws ExecutionException, InterruptedException {
        JiraJqlResult result = JiraSourceClient.build().findByJql(JqlBuilder.build(projectKey, List.of(), List.of()));
        return result.getIssues();
    }

    public List<JiraIssueSource> findByProjectKeyAndUpdatedSince(String projectKey, ZonedDateTime updatedSince) {
        return null;
    }

}