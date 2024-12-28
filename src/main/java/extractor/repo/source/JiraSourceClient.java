package extractor.repo.source;

import extractor.persistance.source.JiraIssueSource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;

@RegisterRestClient
public interface JiraSourceClient {

    static JiraSourceClient build() {
        Config config = ConfigProvider.getConfig();
        String baseUri = config.getValue("jira.url", String.class);
        String user = config.getValue("jira.user", String.class);
        String password = config.getValue("jira.password", String.class);
        return RestClientBuilder.newBuilder().baseUri(baseUri).header("Authorization", "Basic " + Base64.getEncoder().encodeToString((user + ":" + password).getBytes())).build(JiraSourceClient.class);
    }

    @GET
    @Path("/rest/api/3/search")
    JiraJqlResult findByJql(@QueryParam("jql") String jql);

}
