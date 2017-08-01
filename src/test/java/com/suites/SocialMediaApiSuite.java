package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.uss38.GetCircleLiveStatusTest;
import com.tests.uss38.GetCirclesInfoTest;
import com.tests.uss38.GetLiveStreamsTest;
import com.tests.uss38.GetLoginUrlInfoTest;
import com.tests.uss38.GetMeInfoTest;
import com.tests.uss38.GetPostsComments;
import com.tests.uss38.GetPostsReplyComments;
import com.tests.uss38.PostCommentsTest;
import com.tests.uss38.PostKloutTest;
import com.tests.uss38.PostReplyCommentsTest;
import com.tests.uss38.PostSyncTest;
import com.tests.uss38.PostSyncmediaTest;

@SuiteClasses({
	GetLoginUrlInfoTest.class,
	GetMeInfoTest.class,
	GetCirclesInfoTest.class,
	GetLiveStreamsTest.class,
	GetCircleLiveStatusTest.class,
	
	PostCommentsTest.class,
	GetPostsComments.class,
	
	PostReplyCommentsTest.class,
	GetPostsReplyComments.class,
	
	PostSyncTest.class,
	PostSyncmediaTest.class,
	PostKloutTest.class,
})
@RunWith(Suite.class)
public class SocialMediaApiSuite {

}
