package prog.teampoule.applitest;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by Julien on 20/03/2017.
 */

public class activity_Default extends Menu {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_default);
        View inflated = stub.inflate();
    }
}