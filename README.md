# rizomm-filemanager

## Request authentication

Create a free account on [https://developer.okta.com/]()

Follow this step : [https://developer.okta.com/docs/guides/implement-implicit/overview/]() to create a Single Page Application

Go to this uri in your nav
`https://{your-octa-base-uri}.okta.com/oauth2/default/v1/authorize?client_id={your-spa-client-id}&response_type=token&scope=openid&redirect_uri=http://localhost:4200/implicit/callback&state=null&nonce=foo`

The token will be in the redirect uri
`http://localhost:4200/implicit/callback#access_token={the-token-to-copy}&token_type=Bearer&expires_in=3600&scope=openid&state=null`

Copy the bearer

Set the bearer in your API request header
```
curl -X GET \
  http://localhost:8080/users/me \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: Bearer {the-token-to-paste}'
```

Enjoy authenticated