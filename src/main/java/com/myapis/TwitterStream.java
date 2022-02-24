package com.myapis;

import twitter4j.ResponseList;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import java.util.List;

public class TwitterStream {
	
	private static String consumerKey = "";
	private static String consumerSec = "";
	private static String AccessTokenKey = "";
	private static String AccessTokenSec = "";
	
	public static void main (String args[]) {
		
		Twitter twitter = new TwitterFactory().getInstance();
		
		twitter.setOAuthConsumer(consumerKey, consumerSec);
		AccessToken token = new AccessToken(AccessTokenKey, AccessTokenSec);
		
		twitter.setOAuthAccessToken(token);
		
		try {
			
			//To get the list of tweets for authenticated user
			ResponseList<Status> statusResponseList = twitter.getUserTimeline(new Paging(1, 5));
			
			for (Status status: statusResponseList) {
				System.out.println(status.getText());
			}
			
			//To get the list of tweets for any specified user
			User user = twitter.showUser("Abhishekkar_");
			System.out.println("Tweets for " + user.getName());
			List<Status> userStats = twitter.getUserTimeline(user.getId());
			for (Status status: userStats) {
				System.out.println(status.getText());
			}
			
			//twitter.updateStatus("Posting using twitter4j.");			
			
			System.out.println("Successfully posted to twitter!");
			
		} catch(TwitterException te ) {
			te.printStackTrace();
		}
	}
}