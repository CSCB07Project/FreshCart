import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.myapplication.Buyer;
import com.google.firebase.database.FirebaseDatabase;

@RunWith(MockitoJUnitRunner.class)
public class BuyerTest {
    @Test
    public void testChangeUsername(){
        Buyer test = new Buyer("1", "test","test","test","test");
        assertTrue(true);
    }

}
