package EchotuneClient.test;

import java.util.Date;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import EchotuneClient.test.songs.TestAddSongs;
import EchotuneClient.test.songs.TestRemoveSongs;
import EchotuneClient.test.ui.TestSceneLoading;
import EchotuneClient.test.users.TestAddUsers;
import EchotuneClient.test.users.TestRemoveUsers;

public class TestRunner {
	public static void testAll() {
		TestRunner.runTest(TestAddUsers.class);
		TestRunner.runTest(TestRemoveUsers.class);
		TestRunner.runTest(TestAddSongs.class);
		TestRunner.runTest(TestRemoveSongs.class);
		TestRunner.runTest(TestSceneLoading.class);
	}
	
	public static void runTest(Class<?> classToTest) {
		Date before = new Date();
		Result testAddUsersResult = JUnitCore.runClasses(classToTest);
		Date after = new Date();
		
		for(Failure fail: testAddUsersResult.getFailures()) {
			System.out.println(fail.toString());
		}
		System.out.println(classToTest.getName() + " was successful: " + testAddUsersResult.wasSuccessful());
		System.out.println(classToTest.getName() + " ran in " + (after.getTime() - before.getTime()) + "ms");
	}
}
