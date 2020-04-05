# random-server-endpoint

To run, start up the node server by running "node index.js" inside the node-server directory.
Then cd into the random-wait-endpoint directory and run "./mvnw spring-boot:run"

Some issues I ran into that I was trying to fix/debug:
When running within "random-wait-endpoint" (pointing to the JobIdRouter and Handler inside the project) and when trying to hit the same route (different port - 8080) in "random-wait-endpoint-server" I would get connection timeout errors of serveral varieties: 

PrematureCloseException: Connection has been closed BEFORE response, while sending request body
io.netty.channel.AbstractChannel$AnnotatedConnectException: Operation timed out: localhost/127.0.0.1:8080
connection reset by peer

Therefore, it is recommended running the spring webclient against the node server. This setup works the vast majority of the time and is much more performant. 

I tried a lot of different configurations to try to keep the connection alive. In turn, I did a lot of research into this and it seems like there are a lot of other developers that a running into similar issues. However, there doesn't seem to be a lot of clear direction nor workarounds to solve this issue. At least not yet... 

Violeta Georgieva (@violetagg) on the Reactor Project is pretty active in the reactor-netty github issues trying to help developers with their specific issues

In addition, I recently listened to an episode of A Bootiful Podcast, episode 51 released 2/5/2020, in which she was a guest and she had acknowledged there are several issues with the netty server that the team is currently working to fix and hopes to have a new release out in the next couple of months.

All in all, even though this wasn't a perfect solution it was still great to work with netty and Spring Webflux to try to solve this challenge. Netty shows a lot of promise in terms of Asynchronus programming.