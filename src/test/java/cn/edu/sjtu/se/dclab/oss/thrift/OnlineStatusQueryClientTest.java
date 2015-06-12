package cn.edu.sjtu.se.dclab.oss.thrift;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by francis on 6/9/15.
 */
public class OnlineStatusQueryClientTest {

    @Test
    public void testCheckOnline() throws Exception {
        OnlineStatusQueryClient client = new OnlineStatusQueryClient();
        assertEquals(client.checkOnline("15"), "[\"testClientId\"]");
    }
}
