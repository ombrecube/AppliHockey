package prog.teampoule.applitest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by Julien on 20/03/2017.
 */

public class activity_Calendrier extends Menu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_calendrier);
        View inflated = stub.inflate();
    }
}
