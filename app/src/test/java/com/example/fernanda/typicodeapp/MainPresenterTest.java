package com.example.fernanda.typicodeapp;

import com.example.fernanda.typicodeapp.model.User;
import com.example.fernanda.typicodeapp.presenter.MainPresenterImpl;
import com.example.fernanda.typicodeapp.ui.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;


import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by fernanda on 17/11/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainPresenterTest {

    private String USER_LIST = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Leanne Graham\",\n" +
            "    \"username\": \"Bret\",\n" +
            "    \"email\": \"Sincere@april.biz\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Kulas Light\",\n" +
            "      \"suite\": \"Apt. 556\",\n" +
            "      \"city\": \"Gwenborough\",\n" +
            "      \"zipcode\": \"92998-3874\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"-37.3159\",\n" +
            "        \"lng\": \"81.1496\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"phone\": \"1-770-736-8031 x56442\",\n" +
            "    \"website\": \"hildegard.org\",\n" +
            "    \"company\": {\n" +
            "      \"name\": \"Romaguera-Crona\",\n" +
            "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "      \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Ervin Howell\",\n" +
            "    \"username\": \"Antonette\",\n" +
            "    \"email\": \"Shanna@melissa.tv\",\n" +
            "    \"address\": {\n" +
            "      \"street\": \"Victor Plains\",\n" +
            "      \"suite\": \"Suite 879\",\n" +
            "      \"city\": \"Wisokyburgh\",\n" +
            "      \"zipcode\": \"90566-7771\",\n" +
            "      \"geo\": {\n" +
            "        \"lat\": \"-43.9509\",\n" +
            "        \"lng\": \"-34.4618\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"phone\": \"010-692-6593 x09125\",\n" +
            "    \"website\": \"anastasia.net\",\n" +
            "    \"company\": {\n" +
            "      \"name\": \"Deckow-Crist\",\n" +
            "      \"catchPhrase\": \"Proactive didactic contingency\",\n" +
            "      \"bs\": \"synergize scalable supply-chains\"\n" +
            "    }\n" +
            "  }" +
            "]";

    private Retrofit retrofit;
    private MockWebServer mockWebServer;

    @Mock private MainView view;
    private MainPresenterImpl mainPresenter;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        configMockWebServer();
        FakeHttpCall fakeHttpCall = new FakeHttpCall();
        retrofit = fakeHttpCall.getRetrofit(USER_LIST, mockWebServer);
        mainPresenter = new MainPresenterImpl(retrofit, view);
    }

    @Test
    public void shouldSuccessWithLoadUsers() {
        List<User> users = new ArrayList<>();
        User user = new User("Ervin Howell", "Antonette", "Shanna@melissa.tv", "010-692-6593 x09125", "anastasia.net", 2);
        User user2 = new User("Leanne Graham", "Bret", "Sincere@april.biz", "1-770-736-8031 x56442", "hildegard.org", 1);
        users.add(user);
        users.add(user2);

        mainPresenter.loadUsers();
        verify(view).showUsers(eq(users));
    }

    public void configMockWebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(USER_LIST).setResponseCode(HttpURLConnection.HTTP_OK));
        mockWebServer.start();
    }

    @After
    public void teardown() throws Exception{
        mockWebServer.shutdown();
    }
}
