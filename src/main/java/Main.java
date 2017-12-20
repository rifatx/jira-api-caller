/**
 * Created by r on 19-Dec-17.
 */

import com.atlassian.jira.rest.client.api.*;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.User;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI uri = new URI("http://10.240.0.199");
        JiraRestClient client = factory.createWithBasicHttpAuthentication(uri, "rifat", "12345");

        // Invoke the JRJC Client
        Promise<User> promise = client.getUserClient().getUser("dev1");
        User user = promise.claim();

        Promise<Issue> promiseIssue = client.getIssueClient().getIssue("TESTPROJ-5");
        Issue issue = promiseIssue.claim();

        // Print the result
        System.out.println(String.format("Your admin user's email address is: %s\r\n", user.getEmailAddress()));

        // Print the result
        System.out.println("some TEST-1 details " + issue.getAssignee() + "   " + issue.getSummary() + "  " + issue.getWorklogs());

        // Done
        System.out.println("Example complete. Now exiting.");
    }
}
