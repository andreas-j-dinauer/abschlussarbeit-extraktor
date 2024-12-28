package extractor.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class JiraConfig {

    private final URI uri;
    private final String user;
    private final String password;
    private final List<String> projectKeys;

    @Inject
    public JiraConfig(@ConfigProperty(name = "jira.url") String url, @ConfigProperty(name = "jira.user") String user, @ConfigProperty(name = "jira.password") String password, @ConfigProperty(name = "jira.projectKeys") String projectKeys) {
        this.uri = URI.create(url);
        this.user = user;
        this.password = password;
        this.projectKeys = Arrays.stream(projectKeys.split(",")).map(projectKey -> projectKey.trim()).toList();
    }

    public URI getUri() {
        return uri;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getProjectKeys() {
        return projectKeys;
    }
}