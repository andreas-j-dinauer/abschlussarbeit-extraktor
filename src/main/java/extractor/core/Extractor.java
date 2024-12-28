package extractor.core;

import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import extractor.persistance.source.JiraIssueSource;
import extractor.repo.source.IssueSourceRepo;
import extractor.repo.source.JiraProjectSourceRepo;
import extractor.repo.source.JiraSourceClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class Extractor {

    private final JiraConfig jiraConfig;
    private final JiraProjectSourceRepo jiraProjectSourceRepo;
    private final IssueExtractor issueExtractor;
    private final IssueSourceRepo issueSourceRepo;

    @Inject
    public Extractor(JiraConfig jiraConfig, JiraProjectSourceRepo jiraProjectSourceRepo, IssueExtractor issueExtractor, IssueSourceRepo issueSourceRepo) {
        this.jiraConfig = jiraConfig;
        this.jiraProjectSourceRepo = jiraProjectSourceRepo;
        this.issueExtractor = issueExtractor;
        this.issueSourceRepo = issueSourceRepo;
    }

    public void run() throws ExecutionException, InterruptedException, IOException {
        var x = new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(jiraConfig.getUri(), jiraConfig.getUser(), jiraConfig.getPassword());
        ZonedDateTime now = ZonedDateTime.now(); // Pull last run's datetime here to extract all changes from source system.
        Map<String, List<String>> projectConfigs = new ObjectMapper().readValue(new File("/home/andreas/dev/bachelor/extractor/src/main/resources/config.json"), new TypeReference<Map<String, List<String>>>() {});
        for(Map.Entry<String, List<String>> projectConfig : projectConfigs.entrySet()) {
            String projectKey = projectConfig.getKey();
            List<String> issuetypes = projectConfig.getValue();
            Iterable<Issue> issues = x.getSearchClient().searchJql(JqlBuilder.build(projectKey, issuetypes, List.of())).get().getIssues();
            for(Issue issue : issues) {
                System.out.println(issue.getSummary());
                System.out.println(issue.getDescription());
            }
        }
    }

}