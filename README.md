# GitHubClient

This is a demo project for searching GitHub users and browsing their repositories.

### API Rate Limits

GitHub API has strict [rate limits](https://docs.github.com/en/rest/overview/resources-in-the-rest-api#rate-limiting) for unauthenticated requests (60 requests per hour).  
To increase the limit (up to 5000 requests per hour):

1.  Generate a [personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens) (no special permissions required).

2.  Add it to  `secrets.properties`
