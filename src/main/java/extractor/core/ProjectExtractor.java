package extractor.core;

import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProjectExtractor {

    private final IssueExtractor issueExtractor;

    @Inject
    public ProjectExtractor(IssueExtractor issueExtractor) {
        this.issueExtractor = issueExtractor;
    }

    public void run(Map<String, List<String>> projectConfigs) {
        for(Map.Entry<String, List<String>> projectConfig : projectConfigs.entrySet()) {
            run(projectConfig);
            issueExtractor.run(projectConfig);
        }
    }

    public void run(Map.Entry<String, List<String>> project) {
        
    }

}