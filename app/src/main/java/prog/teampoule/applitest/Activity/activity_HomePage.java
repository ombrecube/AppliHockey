package prog.teampoule.applitest.Activity;

import android.os.Bundle;

import android.view.View;
import android.view.ViewStub;

import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;

public class activity_HomePage extends Menu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_home_page);
        View inflated = stub.inflate();
    }


}
